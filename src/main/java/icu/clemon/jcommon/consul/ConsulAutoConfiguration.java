package icu.clemon.jcommon.consul;

import com.ecwid.consul.v1.ConsulClient;
import icu.clemon.jcommon.route.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.serviceregistry.ServiceRegistryAutoConfiguration;
import org.springframework.cloud.consul.ConditionalOnConsulEnabled;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.discovery.HeartbeatProperties;
import org.springframework.cloud.consul.discovery.TtlScheduler;
import org.springframework.cloud.consul.serviceregistry.ConsulServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnConsulEnabled
@ConditionalOnProperty(value = "spring.cloud.service-registry.enable", matchIfMissing = true)
@AutoConfigureBefore(ServiceRegistryAutoConfiguration.class)
public class ConsulAutoConfiguration {

  @Bean
  @Primary
  public ConsulServiceRegistry commonConsulServiceRegistry(
      ConsulClient consulClient,
      ConsulDiscoveryProperties properties,
      @Autowired(required = false) TtlScheduler ttlScheduler,
      HeartbeatProperties heartbeatProperties,
      @Value("${spring.application.name}") String appName,
      RouteService routeService) {
    return new ServiceRegistry(
        consulClient, properties, ttlScheduler, heartbeatProperties, appName, routeService);
  }
}
