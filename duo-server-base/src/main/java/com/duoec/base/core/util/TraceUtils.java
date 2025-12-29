package com.duoec.base.core.util;

import com.duoec.base.DuoApiConst;
import com.duoec.base.core.DuoServerConsts;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author xuwenzhen
 */
public class TraceUtils {
    private TraceUtils() {
    }

    /**
     * 获得一个跟踪码，如果当前不存在，则新创建一个并写入MDC
     *
     * @return traceId
     */
    public static String getOrCreate() {
        String traceId = MDC.get(DuoApiConst.TRACE_ID_NAME);
        if (traceId == null || traceId.length() == 0) {
            traceId = getNewTraceId();
        }
        setTraceId(traceId);
        return traceId;
    }

    public static String get() {
        return MDC.get(DuoApiConst.TRACE_ID_NAME);
    }

    /**
     * 获取下一个跟踪码，如果没有则创建，有则在当前基础上添加一段(不写MDC)
     */
    public static String getNextOrCreate() {
        String traceId = MDC.get(DuoApiConst.TRACE_ID_NAME);
        if (traceId == null || traceId.length() == 0) {
            return getNewTraceId();
        }
        return getNext(traceId);
    }

    /**
     * 获取下一级traceId，如果不存在，则创建个新的（不写MDC）
     *
     * @param traceId 当前存在的traceId
     * @return 下一级traceId
     */
    public static String getNext(String traceId) {
        if (StringUtils.isEmpty(traceId)) {
            return getNewTraceId();
        }
        return traceId + DuoServerConsts.MIDDLE_LINE_STR + getNewTraceId();
    }

    /**
     * 设置指定的traceId
     *
     * @param traceId 跟踪码
     */
    public static void setTraceId(String traceId) {
        Thread.currentThread().setName(traceId);
        MDC.put(DuoApiConst.TRACE_ID_NAME, traceId);
    }

    public static void clear() {
        MDC.remove(DuoApiConst.TRACE_ID_NAME);
        MDC.remove(DuoApiConst.STR_TENANT_ID);
    }

    public static String getNewTraceId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

}
