package com.surup.websocket_chat.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.function.Predicate;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
  OK(0, HttpStatus.OK, "ok"),

  BAD_REQUEST(1000, HttpStatus.BAD_REQUEST, "bad request"),
  VALIDATION_ERROR(1001, HttpStatus.BAD_REQUEST, "validation error"),

  INTERNAL_SERVER_ERROR(2000, HttpStatus.INTERNAL_SERVER_ERROR, "internal server error"),
  DATA_ACCESS_ERROR(2001, HttpStatus.INTERNAL_SERVER_ERROR, "data access error"),
  USER_REGISTER_ERROR(2002, HttpStatus.INTERNAL_SERVER_ERROR, "user register error")
  ;

  private final Integer code;
  private final HttpStatus httpStatus;
  private final String message;

  public String getMessage(String message) {
    return Optional.ofNullable(message)
            .filter(Predicate.not(String::isBlank))
            .orElse(this.getMessage());
  }

  public String getMessage(Throwable cause) {
    return this.getMessage(this.getMessage() + " - " + cause.getMessage());
  }

  @Override
  public String toString() {
    return String.format("%s (%d)", this.name(), this.getCode());
  }
}
