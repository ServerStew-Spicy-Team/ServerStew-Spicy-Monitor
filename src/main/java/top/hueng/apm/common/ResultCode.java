package top.hueng.apm.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * ClassName: ResultCode
 * Package: top.hueng.apm.common
 * Description:
 *
 * @Author: oceanLover
 * @Create: 2023/4/14 - 23:26
 * @Version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ResultCode implements IErrorCode{

    SUCCESS(0, "操作成功"),
    FAILED(1, "请求失败"),
    VALID_DATA_FAILED(2, "参数错误");

    private long code;

    private String message;

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ResultCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
