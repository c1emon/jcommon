package icu.clemon.common.exception;

import icu.clemon.common.http.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class APIException extends RuntimeException {
    private int code;
    private String Message;

    private Object data;

    public APIException(ResultCode rc, String message, Object data) {
        this.code = rc.getCode();
        this.Message = message;
        this.data = data;
    }

    public APIException(ResultCode rc, String message) {
        this.code = rc.getCode();
        this.Message = message;
        this.data = null;
    }

    public APIException(ResultCode rc, Object data) {
        this.code = rc.getCode();
        this.Message = rc.getMsg();
        this.data = data;
    }

    public APIException(ResultCode rc) {
        this.code = rc.getCode();
        this.Message = rc.getMsg();
        this.data = null;
    }

}
