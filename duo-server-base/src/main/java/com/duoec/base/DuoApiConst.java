package com.duoec.base;

/**
 * @author xuwenzhen
 */
public class DuoApiConst {

    private DuoApiConst() {
    }

    public static final String TRACE_ID_NAME = "trace-id";
    public static final String STR_TENANT_ID = "tid";

    /**
     * 接口正常响应
     */
    public static final int API_SUCCESS_CODE = 0;

    /**
     * 接口错误响应
     */
    public static final int API_ERROR_CODE = 500;

    /**
     * 未登录
     */
    public static final int API_ERROR_CODE_UNAUTHORIZED = 40100;

    /**
     * JWT已过期
     */
    public static final int API_ERROR_CODE_JWT_EXPIRED = 40101;

    /**
     * 账号变更
     */
    public static final int API_ERROR_CODE_AUTHOR_CHANGE = 40102;

    /**
     * JWT无效
     */
    public static final int API_ERROR_CODE_JWT_INVALID = 40103;

    /**
     * JWT签名无效
     */
    public static final int API_ERROR_CODE_JWT_SIGN = 40104;

    /**
     * 禁止操作（权限不足）
     */
    public static final int API_ERROR_CODE_FORBIDDEN = 40105;

    /**
     * 未登录（游客）
     */
    public static final int API_ERROR_CODE_UNAUTHORIZED_GUEST = 40106;
}
