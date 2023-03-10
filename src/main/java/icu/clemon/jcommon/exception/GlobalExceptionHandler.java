package icu.clemon.jcommon.exception;

import icu.clemon.jcommon.http.Result;
import icu.clemon.jcommon.http.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Object> HttpMessageNotReadableExceptionHandler(Exception e) {
        if (e instanceof HttpMessageNotReadableException ex) {
            if (ex.getRootCause() instanceof APIException exx) {
                log.error("API error:\n");
                e.printStackTrace();
                return Result.error(exx);
            }

        }
        log.error("Http message NotReadable error:\n");
        e.printStackTrace();
        return Result.error(ResultCode.CODE400.getCode(), e.getMessage());
    }

}
