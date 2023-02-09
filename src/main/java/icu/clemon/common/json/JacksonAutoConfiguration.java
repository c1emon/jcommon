package icu.clemon.common.json;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonAutoConfiguration {

    @Bean
    public CommonJackson2ObjectMapperBuilderCustomizer commonJackson2ObjectMapperBuilderCustomizer() {
        return new CommonJackson2ObjectMapperBuilderCustomizer();
    }

}
