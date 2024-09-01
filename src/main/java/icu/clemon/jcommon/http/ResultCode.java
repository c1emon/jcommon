package icu.clemon.jcommon.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResultCode {
  OK(0, "OK"),
  IllegalArgument(1004, "IllegalArgumentException"),
  NotFound(1005, "not found"),
  AlreadyExist(1006, "already exists"),
  HTTP200(HttpStatus.OK.value(), HttpStatus.OK.name()),
  HTTP400(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name()),
  HTTP401(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.name()),
  HTTP403(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.name()),
  HTTP404(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name()),
  HTTP500(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());

  private final int code;
  private final String msg;

  ResultCode(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
