package com.duoec.base.dto.response;

import com.duoec.base.DuoApiConst;

import java.io.Serializable;
import java.util.Objects;

/**
 * 标准响应体
 *
 * @author xuwenzhen
 */
public class BaseResponse<T> implements Serializable {
    /**
     * 响应值： 0=正常 其它值表示有异常
     *
     * @demo 0
     */
    private Integer code;

    /**
     * 信息，一般在响应异常时有值
     *
     * @demo 参数错误（一般code!=0时会有值）
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * traceId，在发生错误时，会设置此值，用于跟踪记录
     */
    private String tid;

    public static <T> BaseResponse<T> success() {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(DuoApiConst.API_SUCCESS_CODE);
        return response;
    }

    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(DuoApiConst.API_SUCCESS_CODE);
        response.setData(data);
        return response;
    }

    public static <T> BaseResponse<T> error() {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(DuoApiConst.API_ERROR_CODE);
        return response;
    }

    public static <T> BaseResponse<T> error(String msg) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(DuoApiConst.API_ERROR_CODE);
        response.setMsg(msg);
        return response;
    }

    public static <T> BaseResponse<T> error(String msg, T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(DuoApiConst.API_ERROR_CODE);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static <T> BaseResponse<T> error(Integer code, String msg) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    /**
     * 响应状态是否正确，检查响应不为null 且 code = 0
     *
     * @param resp 接口标准响应体
     * @return 响应是否正确
     */
    public static boolean responseSuccess(BaseResponse<?> resp) {
        return resp != null && Objects.equals(DuoApiConst.API_SUCCESS_CODE, resp.getCode());
    }

    /**
     * 响应状态是否正确，检查响应为 code = 500
     *
     * @param resp 接口标准响应体
     * @return 响应是否正确
     */
    public static boolean responseError(BaseResponse<?> resp) {
        return !Objects.equals(DuoApiConst.API_SUCCESS_CODE, resp.getCode());
    }

    /**
     * 响应状态是否正确，检查响应不为null 且 code = 0 且 data不为null
     *
     * @param resp 接口标准响应体
     * @return 响应是否正确
     */
    public static boolean responseSuccessWithNonNullData(BaseResponse<?> resp) {
        return responseSuccess(resp) && resp.getData() != null;
    }

    /**
     * 响应状态是否正确，检查响应为null 且 code = 500 或 data为null
     *
     * @param resp 接口标准响应体
     * @return 响应是否正确
     */
    public static boolean responseErrorOrNullData(BaseResponse<?> resp) {
        return responseError(resp) || resp.getData() == null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
