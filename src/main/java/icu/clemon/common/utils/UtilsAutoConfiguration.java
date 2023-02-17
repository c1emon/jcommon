package icu.clemon.common.utils;


import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AutoConfiguration
public class UtilsAutoConfiguration {

    @Bean
    public BeanUtils beanUtils() {
        return new BeanUtils();
    }

}
