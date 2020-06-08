package com.example.demo.common.exception;

import com.example.demo.common.error.ErrorCode;
import lombok.Getter;

@Getter
public class PlatformException extends RuntimeException {

    private static final long serialVersionUID = 3059761063697921745L;

    private ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
    private Object data;

    public PlatformException(ErrorCode errorCode) {
        this(errorCode, (Object) null);
    }

    public PlatformException(ErrorCode errorCode, Exception cause) {
        this(errorCode, (String) null, cause);
    }

    public PlatformException(ErrorCode errorCode, Object[] args) {
        this(errorCode, String.format(errorCode.getMessage(), args), null);
    }

    public PlatformException(ErrorCode errorCode, Object[] args, Exception cause) {
        this(errorCode, String.format(errorCode.getMessage(), args), cause);
    }

    public PlatformException(ErrorCode errorCode, String message, Exception cause) {
        this(errorCode, message, null, cause);
    }

    protected PlatformException(ErrorCode errorCode, Object data) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.data = data;
    }

    protected PlatformException(ErrorCode errorCode, String message, Object data, Exception cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.data = data;
    }
}
