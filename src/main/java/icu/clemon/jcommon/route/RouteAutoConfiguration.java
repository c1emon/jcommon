package icu.clemon.jcommon.route;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class RouteAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public RouteService routeService(WebApplicationContext webApplicationContext) {
    return new RouteService(webApplicationContext);
  }
}
