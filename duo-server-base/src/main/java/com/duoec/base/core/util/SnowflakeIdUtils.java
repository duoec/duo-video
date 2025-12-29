package com.duoec.base.core.util;

import com.duoec.base.exceptions.DuoServiceException;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 雪花ID生成器
 *
 * @author xuwenzhen
 */
public class SnowflakeIdUtils {
    /**
     * 开始时间截 (2024-01-10 00:00:00)
     */
    private static final long START_TIME_STAMP = 1704816000L;

    /**
     * 应用实例ID所占的位数
     */
    private static final long WORKER_ID_BITS = 16L;

    /**
     * 序列在id中占的位数
     */
    private static final long SEQUENCE_BITS = 16L;

    /**
     * 时间截向左移32位(16+16)
     */
    private static final long TIME_STAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 支持的最大应用实例，结果是65535 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private static final long MAX_APP_INSTANCE = ~(-1L << WORKER_ID_BITS);

    /**
     * 生成序列的掩码 (0b1111111111111111=0xffff=65535)
     */
    private static final Integer SEQUENCE_MASK = ~(-1 << SEQUENCE_BITS);

    /**
     * 应用实例ID(0~65535)
     */
    private final long appInstanceId;

    /**
     * 秒内序列(0~65535)
     */
    private final AtomicInteger sequence = new AtomicInteger(0);

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    private static final SnowflakeIdUtils TMP_SNOWFLAKE_ID = new SnowflakeIdUtils(1, System.currentTimeMillis() / 1000);

    //==============================Constructors=====================================

    /**
     * 构造函数
     *
     * @param appInstanceId 应用实例ID (0~65535)
     * @param lastTimestamp 上一个时间戳，粒度：秒
     */
    public SnowflakeIdUtils(long appInstanceId, long lastTimestamp) {
        this.appInstanceId = appInstanceId % MAX_APP_INSTANCE;
        long now = timeGen();
        if (this.lastTimestamp >= now) {
            throw new IllegalArgumentException(String.format("系统时间 %d < lastTimestamp %s", now, this.lastTimestamp));
        }
        this.lastTimestamp = lastTimestamp;
    }

    // ==============================Methods==========================================

    public static long nextTmpId() {
        return TMP_SNOWFLAKE_ID.nextId();
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new DuoServiceException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        long seq = 0;
        //如果是同一时间生成的，则进行秒内序列
        if (lastTimestamp == timestamp) {
            seq = sequence.addAndGet(1);
            //毫秒内序列溢出
            if ((seq & SEQUENCE_MASK) == 0) {
                //阻塞到下一个秒,获得新的时间戳
                timestamp = tilNextSeconds(lastTimestamp);
            }
        } else {
            //时间戳改变，毫秒内序列重置
            sequence.set(0);
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - START_TIME_STAMP) << TIME_STAMP_LEFT_SHIFT)
                | (seq << SEQUENCE_BITS)
                | appInstanceId;
    }

    /**
     * 阻塞到下一秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private long tilNextSeconds(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis() / 1000;
    }

    public long getLastTimestamp() {
        return lastTimestamp;
    }
}