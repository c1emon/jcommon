package icu.clemon.jcommon.exception;

import icu.clemon.jcommon.http.ResultCode;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class APIException extends RuntimeException {
    private final int code;
    private final String Message;
    @Nullable
    private final Object data;

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

    public APIException(int code, String Message, Object data) {
        this.code = code;
        this.Message = Message;
        this.data = data;
    }

    public APIException(int code,String Message) {
        this.code = code;
        this.Message = Message;
        this.data = null;
    }

}
