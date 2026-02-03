#!/usr/bin/env python3
"""
视频上传处理器
支持多种上传方式：接口上传、OSS上传、COS上传
"""

import os
import configparser
from abc import ABC, abstractmethod
from typing import Optional, Dict, Any, Tuple

# 导入日志模块
from logger import logger, info, error, warning, debug

# 导入API客户端
from api_client import APIClient


class UploadStrategy(ABC):
    """上传策略抽象基类"""
    
    @abstractmethod
    def upload(self, video_file_path: str, video_id: str) -> Tuple[bool, Optional[str], str]:
        """
        上传视频文件
        
        :param video_file_path: 视频文件路径
        :param video_id: 视频ID
        :return: (是否成功, 文件URL, 错误消息)
        """
        pass


# API上传方式已被移除，现在只支持OSS和COS上传


class OSSUploadStrategy(UploadStrategy):
    """阿里云OSS上传"""
    
    def __init__(self, config: Dict[str, Any]):
        self.bucket_name = config.get('oss_bucket_name', '')
        self.access_key_id = config.get('oss_access_key_id', '')
        self.access_key_secret = config.get('oss_access_key_secret', '')
        self.endpoint = config.get('oss_endpoint', '')
        self.region = config.get('oss_region', '')
        
        # 检查必要配置
        if not all([self.bucket_name, self.access_key_id, self.access_key_secret, self.endpoint]):
            raise ValueError("OSS配置不完整，请检查secret_id/secret_key等配置项")
        
        # 尝试导入OSS SDK
        try:
            import oss2
            self.oss2 = oss2
        except ImportError:
            raise ImportError("请安装oss2库: pip install oss2")
    
    def upload(self, video_file_path: str, object_key: str) -> Tuple[bool, Optional[str], str]:
        """
        上传视频文件到OSS
        
        :param video_file_path: 视频文件路径
        :param object_key: 视频存储KEY
        :return: (是否成功, 文件URL, 错误消息)
        """
        try:
            # 创建OSS认证
            auth = self.oss2.Auth(self.access_key_id, self.access_key_secret)
            bucket = self.oss2.Bucket(auth, self.endpoint, self.bucket_name)
            
            # 上传文件
            result = bucket.put_object_from_file(object_key, video_file_path)
            
            if result.status == 200:
                # 生成访问URL
                file_url = f"https://{self.bucket_name}.{self.endpoint}/{object_key}"
                info(f"OSS上传成功: {file_url}")
                return True, file_url, "上传成功"
            else:
                return False, None, f"OSS上传失败，状态码: {result.status}"
                
        except Exception as e:
            error(f"OSS上传视频时发生错误: {e}")
            return False, None, str(e)


class COSUploadStrategy(UploadStrategy):
    """腾讯云COS上传"""

    def __init__(self, config: Dict[str, Any]):
        self.bucket_name = config.get('cos_bucket_name', '')
        self.secret_id = config.get('cos_secret_id', '')
        self.secret_key = config.get('cos_secret_key', '')
        self.region = config.get('cos_region', '')

        # 检查必要配置
        if not all([self.bucket_name, self.secret_id, self.secret_key, self.region]):
            raise ValueError("COS配置不完整，请检查secret_id/secret_key等配置项")

        # 尝试导入COS SDK
        try:
            import qcloud_cos
            self.qcloud_cos = qcloud_cos
        except ImportError:
            raise ImportError("请安装qcloud-cos-v5库: pip install qcloud-cos-v5")

    def upload(self, video_file_path: str, object_key: str) -> Tuple[bool, Optional[str], str]:
        """
        上传视频文件到COS

        :param video_file_path: 视频文件路径
        :param object_key: 视频存储KEY
        :return: (是否成功, 文件URL, 错误消息)
        """
        try:
            # 创建COS客户端
            cos_client = self.qcloud_cos.CosConfig(
                SecretId=self.secret_id,
                SecretKey=self.secret_key,
                Region=self.region
            )

            client = self.qcloud_cos.CosS3Client(cos_client)

            # 上传文件
            response = client.put_object(
                Bucket=self.bucket_name,
                Body=open(video_file_path, 'rb'),
                Key=object_key,
                EnableMD5=True
            )

            if response.get('ETag'):
                # 生成访问URL
                file_url = f"https://{self.bucket_name}.cos.{self.region}.myqcloud.com/{object_key}"
                info(f"COS上传成功: {file_url}")
                return True, file_url, "上传成功"
            else:
                return False, None, "COS上传失败，未返回正确响应"

        except Exception as e:
            error(f"COS上传视频时发生错误: {e}")
            return False, None, str(e)


class UploadHandler:
    """上传处理器主类"""

    def __init__(self, config: Dict[str, Any]):
        self.config = config
        self.storage_type = config.get('upload_storage', 'oss').lower()  # 默认改为oss

        # 根据存储类型选择上传策略
        if self.storage_type == 'oss':
            self.strategy = OSSUploadStrategy(config)
        elif self.storage_type == 'cos':
            self.strategy = COSUploadStrategy(config)
        else:
            raise ValueError(f"不支持的上传方式: {self.storage_type}，支持的方式: oss, cos")
    
    def upload_video(self, video_file_path: str, storage_key: str) -> tuple[bool, str | None, str]:
        """
        上传视频文件

        :param video_file_path: 需要上传的文件路径
        :param storage_key: 文件存储的key
        :return: (是否成功, 文件URL, 错误消息)
        """
        try:
            # 验证提供的文件路径是否存在
            if not os.path.exists(video_file_path):
                error(f"提供的视频文件不存在: {video_file_path}")
                return False, None, f"提供的视频文件不存在: {video_file_path}"

            info(f"开始上传视频: {video_file_path} -->[{self.storage_type}]{storage_key}")

            # 执行上传
            success, file_url, message = self.strategy.upload(video_file_path, storage_key)

            if success and file_url:
                return True, file_url, "上传成功"
            else:
                error(f"视频上传失败: {message}")
                return False, None, message

        except Exception as e:
            error(f"上传视频时发生错误: {e}")
            return False, None, str(e)
        