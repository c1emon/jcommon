package icu.clemon.common.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import icu.clemon.common.exception.APIException;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private boolean success;
    private String msg;
    private int code;
    private long ts;
    private T data;

    public Result() {
        this.ts = System.currentTimeMillis();
    }

    public static <T> Result<T> success(int code, String msg, T data) {
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setSuccess(true);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static <T> Result<T> success() {
        return success(ResultCode.CODE200.getCode(), null, null);
    }

    public static <T> Result<T> success(T data) {
        return success(ResultCode.CODE200.getCode(), ResultCode.CODE200.getMsg(), data);
    }

    public static <T> Result<T> success(int code) {
        return success(code, null, null);
    }

    public static <T> Result<T> success(int code, String msg) {
        return success(code, msg, null);
    }

    public static <T> Result<T> error(int code, String msg, T data) {
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setSuccess(false);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static <T> Result<T> error() {
        return error(ResultCode.CODE400.getCode(), null, null);
    }

    public static <T> Result<T> error(int code) {
        return error(code, null, null);
    }

    public static <T> Result<T> error(APIException e) {
        return error(e.getCode(), e.getMsg());
    }

    public static <T> Result<T> error(int code, String msg) {
        return error(code, msg, null);
    }

    @SneakyThrows
    public String toString() {
        var mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

}
