package icu.clemon.template.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
    @RequestMapping("/hello")
    public String Hello() {
        log.info("log from hello");
        return "hello";
    }
}
