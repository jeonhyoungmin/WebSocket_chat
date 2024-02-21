package com.surup.websocket_chat.exception;

import com.surup.websocket_chat.constant.ErrorCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private final ErrorCode errorCode;

    public BaseException() {
        super(ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
        this.errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
    }

    public BaseException(String message) {
        super(ErrorCode.INTERNAL_SERVER_ERROR.getMessage(message));
        this.errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
    }

    public BaseException(String message, Throwable cause) {
        super(ErrorCode.INTERNAL_SERVER_ERROR.getMessage(message), cause);
        this.errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
    }

    public BaseException(Throwable cause) {
        super(ErrorCode.INTERNAL_SERVER_ERROR.getMessage(cause));
        this.errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
    }

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode, String message) {
        super(errorCode.getMessage(message));
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode.getMessage(message), cause);
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(cause), cause);
        this.errorCode = errorCode;
    }

}
