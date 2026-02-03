#!/usr/bin/env python3
"""
下载和解压模块
负责下载ZIP文件并将其解压到指定目录
"""

import os
import zipfile
import tempfile
import requests
import shutil
from typing import Dict, Any

# 导入日志模块
from logger import logger, info, error, warning, debug
from api_client import APIClient


def download_and_extract(config: Dict[str, Any], jyZipUrl: str, task_id: str = None) -> bool:
    """
    下载并解压任务资源
    下载 jyZipUrl 上的zip，并解压到 config[drafts_path]

    :param config: 配置字典
    :param jyZipUrl: ZIP文件的下载链接
    :param task_id: 任务ID，用于向服务器报告进度
    :return: 是否成功下载并解压
    """
    try:
        # 获取草稿目录
        drafts_path = config.get('drafts_path', '')
        if not drafts_path:
            error("配置中未设置草稿目录")
            return False

        # 使用提供的jyZipUrl
        zip_url = jyZipUrl
        if not zip_url:
            error("jyZipUrl 参数不能为空")
            return False

        # 下载ZIP文件
        response = requests.get(zip_url)
        if response.status_code != 200:
            error(f"下载ZIP文件失败，HTTP状态码: {response.status_code}")

            # 推送状态：-11=任务失败
            if task_id:
                api_base_url = config.get("api_base_url", "http://localhost:17026")
                api_headers = config.get("api_headers", {"Authorization": "Bearer token"})
                api_client = APIClient(base_url=api_base_url, headers=api_headers)
                api_client.update_task_status(task_id, -11, {"error": f"下载ZIP文件失败，HTTP状态码: {response.status_code}"})

            return False

        # 创建临时文件保存ZIP
        with tempfile.NamedTemporaryFile(delete=False, suffix='.zip') as temp_zip:
            temp_zip.write(response.content)
            temp_zip_path = temp_zip.name

        # 推送状态：11=下载完成
        if task_id:
            api_base_url = config.get("api_base_url", "http://localhost:17026")
            api_headers = config.get("api_headers", {"Authorization": "Bearer token"})
            api_client = APIClient(base_url=api_base_url, headers=api_headers)
            api_client.update_task_status(task_id, 11, {"info": "下载完成"})

        try:
            # 确保目标目录存在
            os.makedirs(drafts_path, exist_ok=True)

            with zipfile.ZipFile(temp_zip_path, 'r') as zip_ref:
                zip_ref.extractall(drafts_path)

            # 推送状态：12=解压完成
            if task_id:
                api_base_url = config.get("api_base_url", "http://localhost:17026")
                api_headers = config.get("api_headers", {"Authorization": "Bearer token"})
                api_client = APIClient(base_url=api_base_url, headers=api_headers)
                api_client.update_task_status(task_id, 12, {"info": "解压完成"})

            # 删除临时ZIP文件
            os.unlink(temp_zip_path)

            return True
        except Exception as e:
            error(f"解压ZIP文件失败: {e}")

            # 推送状态：-11=任务失败
            if task_id:
                api_base_url = config.get("api_base_url", "http://localhost:17026")
                api_headers = config.get("api_headers", {"Authorization": "Bearer token"})
                api_client = APIClient(base_url=api_base_url, headers=api_headers)
                api_client.update_task_status(task_id, -11, {"error": f"解压ZIP文件失败: {e}"})

            # 删除临时ZIP文件
            if os.path.exists(temp_zip_path):
                os.unlink(temp_zip_path)

            # 删除可能已部分解压的文件
            try:
                for item in os.listdir(drafts_path):
                    item_path = os.path.join(drafts_path, item)
                    if os.path.isfile(item_path):
                        os.remove(item_path)
                    elif os.path.isdir(item_path):
                        shutil.rmtree(item_path)
            except Exception as cleanup_error:
                error(f"清理解压文件失败: {cleanup_error}")

            return False
    except Exception as e:
        error(f"下载或解压过程中发生错误: {e}")

        # 推送状态：-11=任务失败
        if task_id:
            api_base_url = config.get("api_base_url", "http://localhost:17026")
            api_headers = config.get("api_headers", {"Authorization": "Bearer token"})
            api_client = APIClient(base_url=api_base_url, headers=api_headers)
            api_client.update_task_status(task_id, -11, {"error": f"下载或解压过程中发生错误: {e}"})

        return False
    
if __name__ == '__main__':
    config = {
        # 剪映草稿目录
        "drafts_path": "/Users/xuwenzhen/Movies/JianyingPro/User Data/Projects/com.lveditor.draft",

        # API服务器配置
        "api_base_url": "http://localhost:17026",
        "api_headers": {"Authorization": "Bearer token"}
    }

    download_and_extract(config, "https://api.duoec.com/public/001_duo_video.zip", "test_task_123")