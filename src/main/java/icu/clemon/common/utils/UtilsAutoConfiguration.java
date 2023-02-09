package icu.clemon.common.utils;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsAutoConfiguration {

    @Bean
    public BeanUtils beanUtils() {
        return new BeanUtils();
    }

}
