#!/usr/bin/env python3
"""
API客户端
用于处理与服务器的HTTP通信
"""

import requests
import json
import socket
from typing import Optional, Dict, Any, Tuple

from logger import error, info


class APIClient:
    """
    API客户端类
    提供拉取任务、更新任务状态和上传文件的功能
    """
    
    def __init__(self, base_url: str, headers: Optional[Dict[str, str]] = None, server_name: Optional[str] = None):
        """
        初始化API客户端

        :param base_url: API基础URL
        :param headers: 请求头
        :param server_name: 服务器名称，如果未提供则自动获取主机名
        """
        self.base_url = base_url.rstrip('/')
        self.headers = headers or {}
        self.server_name = server_name or socket.gethostname()
        self.session = requests.Session()
        self.session.headers.update(self.headers)
    
    def fetch_task(self) -> Tuple[bool, Optional[Dict[str, Any]], str]:
        """
        拉取任务

        :return: (success: bool, task_data: dict or None, message: str)
        """
        try:
            url = f"{self.base_url}/api/video-task"
            params = {'serverName': self.server_name}
            response = self.session.get(url, params=params)

            if response.status_code == 200:
                data = response.json()
                info(f"API成功: {url} {data}")

                # 检查响应格式
                if data.get('code') == 0 and data.get('data'):
                    return True, data['data'], "任务获取成功"
                elif data.get('code') == 0 and not data.get('data'):
                    return True, None, "暂无任务"
                else:
                    return False, None, f"API返回错误码: {data.get('code')}, 消息: {data.get('message', '未知错误')}"
            else:
                error(f"API错误: {response.status_code}, 响应: {response.text}")
                return False, None, f"HTTP错误: {response.status_code}, 响应: {response.text}"

        except requests.exceptions.ConnectionError:
            return False, None, "连接错误：无法连接到API服务器"
        except requests.exceptions.Timeout:
            return False, None, "超时错误：请求超时"
        except requests.exceptions.RequestException as e:
            return False, None, f"请求错误：{str(e)}"
        except json.JSONDecodeError:
            return False, None, f"响应解析错误：非JSON格式响应"
        except Exception as e:
            return False, None, f"未知错误：{str(e)}"
    
    def update_task_status(self, task_id: str, status, result: Optional[Dict[str, Any]] = None) -> Tuple[bool, str]:
        """
        更新任务状态

        :param task_id: 任务ID
        :param status: 新状态
        :param result: 结果数据
        :return: (success: bool, message: str)
        """
        try:
            url = f"{self.base_url}/api/video-task/state"
            # 构建 VideoTaskUpdateRequest 对象
            payload = {
                'taskId': int(task_id),
                'serverName': self.server_name,
                'status': status
            }

            # 如果 result 存在，则将其内容平铺到 payload 中
            if result:
                for key, value in result.items():
                    payload[key] = value

            info(f"上报状态：status={status} {result}")
            response = self.session.put(url, json=payload)
            
            if response.status_code == 200:
                data = response.json()
                if data.get('code') == 0:
                    return True, "任务状态更新成功"
                else:
                    return False, f"API返回错误: {data.get('message', '未知错误')}"
            else:
                return False, f"HTTP错误: {response.status_code}, 响应: {response.text}"
                
        except requests.exceptions.RequestException as e:
            return False, f"请求错误：{str(e)}"
        except json.JSONDecodeError:
            return False, f"响应解析错误：非JSON格式响应"
        except Exception as e:
            return False, f"未知错误：{str(e)}"


# 示例用法
if __name__ == "__main__":
    # 创建API客户端实例
    client = APIClient(base_url="http://localhost:17026", headers={"Authorization": "Bearer token"})
    
    # 测试拉取任务
    success, task_data, msg = client.fetch_task()
    print(f"拉取任务: {success}, 数据: {task_data}, 消息: {msg}")
    