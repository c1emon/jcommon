package icu.clemon.template.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @RequestMapping("/hello")
    public String Hello() {
        return "hello";
    }
}
