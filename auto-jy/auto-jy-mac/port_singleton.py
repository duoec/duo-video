#!/usr/bin/env python3
"""
基于端口的单实例控制模块
使用端口绑定机制防止程序重复启动
"""

import socket
import sys
import os
import psutil
from typing import Optional


class PortBasedSingleton:
    """
    基于端口的单实例控制器
    通过绑定特定端口来确保只有一个实例在运行
    """

    def __init__(self, port: int = 54321):
        """
        初始化单实例控制器

        :param port: 用于单例控制的端口号
        """
        self.port = port
        self.socket = None
        self.is_locked = False

    def acquire(self) -> bool:
        """
        尝试获取单实例锁（绑定端口）

        :return: 获取锁成功返回True，否则返回False
        """
        try:
            # 创建socket
            self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            self.socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

            # 尝试绑定端口
            self.socket.bind(('127.0.0.1', self.port))
            self.socket.listen(1)

            self.is_locked = True
            print(f"成功绑定端口 {self.port}，获得单实例锁")
            return True

        except OSError:
            # 端口已被占用，说明已有实例在运行
            print(f"端口 {self.port} 已被占用，说明已有实例在运行")
            if self.socket:
                try:
                    self.socket.close()
                except:
                    pass
                self.socket = None
            self.is_locked = False
            return False

    def release(self):
        """释放锁（关闭端口）"""
        global _current_instance

        if self.socket and self.is_locked:
            try:
                self.socket.close()
                print(f"已释放端口 {self.port}")
            except:
                pass
            self.socket = None
            self.is_locked = False

        # 如果这是当前实例，清除全局变量
        if _current_instance is self:
            _current_instance = None

    def terminate_existing(self) -> bool:
        """
        终止已存在的实例（通过查找并终止进程）

        :return: 终止成功返回True，否则返回False
        """
        try:
            # 获取当前进程ID，避免终止自己
            current_pid = os.getpid()

            # 遍历所有进程查找目标进程
            pids_to_kill = []
            for proc in psutil.process_iter(['pid', 'name', 'cmdline']):
                try:
                    proc_info = proc.info
                    cmdline = proc_info.get('cmdline', [])
                    proc_name = proc_info.get('name', '').lower()

                    # 检查是否是Python进程
                    if 'python' in proc_name or 'python3' in proc_name:
                        # 检查命令行是否包含main_gui.py
                        if cmdline:
                            cmdline_str = ' '.join(cmdline)
                            if 'main_gui.py' in cmdline_str:
                                pid = proc_info['pid']
                                # 排除当前进程
                                if pid != current_pid:
                                    pids_to_kill.append(pid)
                                    print(f"找到目标进程: PID={pid}, CMD={cmdline_str[:100]}...")
                                else:
                                    print(f"跳过当前进程: PID={pid}")
                except (psutil.NoSuchProcess, psutil.AccessDenied):
                    continue

            # 如果没有找到其他目标进程，直接返回成功
            if not pids_to_kill:
                print("未找到其他目标进程")
                return True

            # 先尝试优雅终止
            terminated = False
            for pid in pids_to_kill:
                try:
                    proc = psutil.Process(pid)
                    print(f"尝试优雅终止进程: PID={pid}")
                    proc.terminate()
                    terminated = True
                except (psutil.NoSuchProcess, psutil.AccessDenied):
                    print(f"进程 {pid} 不存在或无权限")
                    continue

            # 等待3秒看是否成功终止
            if terminated:
                import time
                time.sleep(3)

                # 检查是否有进程仍然存在
                still_alive = []
                for pid in pids_to_kill:
                    try:
                        proc = psutil.Process(pid)
                        if proc.is_running():
                            still_alive.append(pid)
                    except (psutil.NoSuchProcess):
                        pass

                # 如果有进程仍在运行，强制杀死
                if still_alive:
                    print(f"强制杀死剩余进程: {still_alive}")
                    for pid in still_alive:
                        try:
                            proc = psutil.Process(pid)
                            proc.kill()
                        except (psutil.NoSuchProcess, psutil.AccessDenied):
                            pass

            # 等待一段时间确保端口被释放
            time.sleep(2)

            return True

        except Exception as e:
            print(f"终止进程时发生错误: {e}")
            import traceback
            traceback.print_exc()
            return False

    def __del__(self):
        """析构函数，确保释放资源"""
        self.release()


def check_single_instance(port: int = 54321) -> Optional[PortBasedSingleton]:
    """
    检查是否已有实例运行

    :param port: 用于单例控制的端口号
    :return: PortBasedSingleton对象（如果获取锁成功），否则返回None
    """
    global _current_instance

    # 尝试获取锁，只尝试一次，避免长时间阻塞
    singleton = PortBasedSingleton(port)
    if singleton.acquire():
        _current_instance = singleton  # 设置全局变量
        return singleton
    else:
        # 释放资源
        singleton.release()
        return None


# 全局变量存储当前进程的单例实例
_current_instance = None


def is_instance_running(port: int = 54321) -> bool:
    """
    检查是否有实例正在运行

    :param port: 用于单例控制的端口号
    :return: 有实例运行返回True，否则返回False
    """
    global _current_instance

    # 如果当前进程已经获取了锁，则不算作"有实例在运行"
    if _current_instance and _current_instance.is_locked:
        return False

    # 尝试连接，设置较短的超时时间避免阻塞
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(0.1)  # 设置较短的超时时间
        result = s.connect_ex(('127.0.0.1', port))
        s.close()

        # 如果连接成功（result为0），说明端口被占用，有实例在运行
        return result == 0
    except:
        return False


if __name__ == '__main__':
    # 测试代码
    print("测试基于端口的单实例机制")

    # 尝试获取锁
    instance = check_single_instance()
    if instance:
        print("成功获取单实例锁")
        input("按回车键释放锁...")
        instance.release()
        print("锁已释放")
    else:
        print("无法获取锁，可能已有实例在运行")
        if is_instance_running():
            print("确认：已有实例在运行")
        else:
            print("警告：端口被非本程序占用")