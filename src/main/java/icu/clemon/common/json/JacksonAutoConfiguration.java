package icu.clemon.common.json;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AutoConfiguration
public class JacksonAutoConfiguration {

    @Bean
    public CommonJackson2ObjectMapperBuilderCustomizer commonJackson2ObjectMapperBuilderCustomizer() {
        return new CommonJackson2ObjectMapperBuilderCustomizer();
    }

}
