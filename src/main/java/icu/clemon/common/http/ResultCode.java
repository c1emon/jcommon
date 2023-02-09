package icu.clemon.common.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ResultCode {
    CODE200(HttpStatus.OK.value(), HttpStatus.OK.name()),
    CODE400(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name()),
    CODE401(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.name()),
    CODE403(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.name()),
    CODE404(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name()),
    CODE500(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());

    @Getter
    private final int code;
    @Getter
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
