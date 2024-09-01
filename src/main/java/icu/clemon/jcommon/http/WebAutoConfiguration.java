package icu.clemon.jcommon.http;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@AutoConfiguration
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

  @Bean
  public Filter httpGetSnakeCaseConverter() {
    return new HttpGetSnakeCaseConverter();
  }
}
