package com.duoec.base.exceptions;

import com.duoec.base.DuoApiConst;

/**
 * 运行时异常
 *
 * @author xuwenzhen
 * @since 2021/5/10 5:46 PM
 */
public class DuoServiceException extends RuntimeException {
    /**
     * 错误状态码
     *
     * @demo 500
     */
    private Integer code = DuoApiConst.API_ERROR_CODE;

    public DuoServiceException(String message) {
        super(message);
    }

    public DuoServiceException(Throwable ex) {
        super(ex);
    }

    public DuoServiceException(String message, Throwable ex) {
        super(message, ex);
    }

    public DuoServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public DuoServiceException(Integer code, String message, Throwable ex) {
        super(message, ex);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
