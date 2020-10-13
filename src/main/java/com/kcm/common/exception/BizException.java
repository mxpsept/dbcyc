package com.kcm.common.exception;

import com.kcm.common.enums.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义业务异常
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/8/28 10:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -7537109310923551996L;

    private Integer errorCode;

    private String errorMsg;

    public BizException() {
        super();
    }

    public BizException(ResultCode resultCode) {
        super(String.valueOf(resultCode.getCode()));
        this.errorCode = resultCode.getCode();
        this.errorMsg = resultCode.getMessage();
    }

    public BizException(ResultCode resultCode, Throwable cause) {
        super(String.valueOf(resultCode.getCode()), cause);
        this.errorCode = resultCode.getCode();
        this.errorMsg = resultCode.getMessage();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(Integer errorCode, String errorMsg) {
        super(String.valueOf(errorCode));
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(Integer errorCode, String errorMsg, Throwable cause) {
        super(String.valueOf(errorCode), cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
    
}
