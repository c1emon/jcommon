package icu.clemon.jcommon.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@AllArgsConstructor
public class CommonWebMvcConfigurer implements WebMvcConfigurer {

    private final ObjectMapper mapper;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
        // remove default string converter
        converters.removeIf(httpMessageConverter -> httpMessageConverter.getClass().equals(StringHttpMessageConverter.class));
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        WebMvcConfigurer.super.addFormatters(registry);
//        registry.addConverter(new EnumeratorConditionalGenericConverter(mapper));
    }
}
