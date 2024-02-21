package com.surup.websocket_chat.controller.error;

import com.surup.websocket_chat.constant.ErrorCode;
import com.surup.websocket_chat.exception.BaseException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ModelAndView base(BaseException e) {
        ErrorCode errorCode = e.getErrorCode();

        return new ModelAndView(
                "error",
                Map.of(
                        "statusCode", errorCode.getHttpStatus().value(),
                        "errorCode", errorCode,
                        "message", errorCode.getMessage()
                ),
                errorCode.getHttpStatus()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView invalid() {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;

        return new ModelAndView(
                "error",
                Map.of(
                        "statusCode", httpStatus.value(),
                        "errorCode", errorCode,
                        "message", errorCode.getMessage()
                ),
                httpStatus
        );
    }

    /*
    TODO: 서버 유효성 검사 실패 시, 요청 값을 다시 뷰로 전달하는 기능 추가할지 검토, 또는 다른 방법 연구
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView invalid(HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;

        return new ModelAndView(
                "signup",
                Map.of(
                        "username", request.getParameter("username"),
                        "password", request.getParameter("password"),
                        "nickname", request.getParameter("nickname")
                ),
                httpStatus
        );
    }
     */

    @ExceptionHandler
    public ModelAndView exception(Exception e, HttpServletResponse response) {
        HttpStatus httpStatus = HttpStatus.valueOf(response.getStatus());
        ErrorCode errorCode = httpStatus.is4xxClientError() ? ErrorCode.BAD_REQUEST : ErrorCode.INTERNAL_SERVER_ERROR;

        if (httpStatus == httpStatus.OK) {
            httpStatus = HttpStatus.FORBIDDEN;
            errorCode = errorCode.BAD_REQUEST;
        }

        return new ModelAndView(
                "error",
                Map.of(
                        "statusCode", httpStatus.value(),
                        "errorCode", errorCode,
                        "message", errorCode.getMessage()
                ),
                httpStatus
        );
    }
}
