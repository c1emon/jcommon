package icu.clemon.jcommon.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

@AutoConfiguration
public class JacksonAutoConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer commonJackson2ObjectMapperBuilderCustomizer() {
        return new CommonJackson2ObjectMapperBuilderCustomizer();
    }

    @Bean
    public ConditionalGenericConverter conditionalGenericConverter(ObjectMapper mapper) {
        return new EnumeratorConditionalGenericConverter(mapper);
    }

}
