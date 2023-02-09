package icu.clemon.common.http;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class WebAutoConfiguration {

    private final BasicErrorController basicErrorController;

    @Bean
    public GlobalResponseHandler globalResponseHandler() {
        return new GlobalResponseHandler();
    }

    @Bean
    public CommonWebMvcConfigurer commonWebMvcConfigurer() {
        return new CommonWebMvcConfigurer();
    }

    @Bean
    public CommonErrorController commonErrorController() {
        return new CommonErrorController(basicErrorController);
    }

}
