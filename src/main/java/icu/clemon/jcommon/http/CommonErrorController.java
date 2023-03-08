package icu.clemon.jcommon.http;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommonErrorController {

    private final BasicErrorController basicErrorController;

    // 覆盖BasicErrorController的error方法
    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<Object> error(HttpServletRequest request, HttpServletResponse response) {
        var errDetail = basicErrorController.error(request);
        log.error(errDetail.toString());
        return Result.error(errDetail.getStatusCode().value(), errDetail.getBody().getOrDefault("error", "").toString());
    }
}
