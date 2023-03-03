package icu.clemon.jcommon.utils;


import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class UtilsAutoConfiguration {

    @Bean
    public BeanUtils beanUtils() {
        return new BeanUtils();
    }

}
