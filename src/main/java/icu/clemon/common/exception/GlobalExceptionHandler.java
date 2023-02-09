package icu.clemon.common.exception;

import icu.clemon.common.http.Result;
import icu.clemon.common.http.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(APIException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> APIExceptionHandler(Exception e) {
        if (e instanceof APIException ex) {
            log.error("API error:\n");
            e.printStackTrace();
            return Result.error(ex);
        }
        log.error("Uncached API error:\n");
        e.printStackTrace();
        return Result.error(ResultCode.CODE500.getCode(), e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public Result<String> nullPointerExceptionHandler(NullPointerException e) {
        log.error("NullPointer error:");
        e.printStackTrace();
        return Result.error(ResultCode.CODE500.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> UnknownHandler(Exception e) {
        log.error("Unknown error:\n");
        e.printStackTrace();
        return Result.error(ResultCode.CODE500.getCode(), e.getMessage());
    }

}
