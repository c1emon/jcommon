package icu.clemon.jcommon.json;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class JacksonAutoConfiguration {

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer commonJackson2ObjectMapperBuilderCustomizer() {
    return new CommonJackson2ObjectMapperBuilderCustomizer();
  }
}
