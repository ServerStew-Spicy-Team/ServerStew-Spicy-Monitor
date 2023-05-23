package top.hueng.apm.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className CommonResult
 * @description 通用返回类
 * @auther wulongdog
 * @date 2022/10/3  17:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    /**
     * 状态码
     */
    private long code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 封装好的数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    /**
     * 成功返回结果
     *
     * @param msg 返回消息
     * @return CommonResult<T>
     */
    public static <T> CommonResult<T> success(String msg) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), msg, null);
    }


    /**
     * 成功返回结果
     *
     * @param data 返回数据
     * @return CommonResult<T>
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data    返回数据
     * @param message 返回消息
     * @return CommonResult<T>
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);

    }


    /**
     * 失败返回结果
     *
     * @param code 业务代码
     * @return CommonResult<T>
     */
    public static <T> CommonResult<T> failed(IErrorCode code) {
        return new CommonResult<T>(code.getCode(), code.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param code    业务代码
     * @param message 返回消息
     * @return CommonResult<T>
     */
    public static <T> CommonResult<T> failed(IErrorCode code, String message) {
        return new CommonResult<T>(code.getCode(), code.getMessage() + " " + message, null);
    }

    /**
     * 失败返回结果
     *
     * @param message 返回消息
     * @return CommonResult<T>
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     *
     * @return CommonResult<T>
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 带数据的失败返回
     *
     * @param detail 错误信息
     * @return CommonResult<T>
     */
    public static <T> CommonResult<T> failedWithDetail(T detail) {
        return new CommonResult<>(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage(), detail);
    }

    /**
     * 参数检验失败返回结果
     *
     * @return CommonResult<T>
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALID_DATA_FAILED);
    }

    /**
     * 参数检验失败返回结果
     *
     * @param message 返回消息
     * @return CommonResult<T>
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return failed(ResultCode.VALID_DATA_FAILED, message);
    }

    /**
     * 未登陆返回结果
     *
     * @param unauthorized unauthorized业务ResultCode
     * @return CommonResult<T>
     */
    public static <T> CommonResult<T> unauthorized(IErrorCode unauthorized) {
        return new CommonResult<T>(unauthorized.getCode(), unauthorized.getMessage(), null);
    }

    /**
     * 未登陆返回结果
     *
     * @param data 返回数据
     * @return CommonResult<T>
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<>(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     *
     * @param data 返回数据
     * @return CommonResult<T>
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<>(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage(), data);
    }
}
