package icu.clemon.jcommon.consul;

import com.ecwid.consul.v1.ConsulClient;
import icu.clemon.jcommon.route.RouteService;
import java.util.LinkedList;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.discovery.HeartbeatProperties;
import org.springframework.cloud.consul.discovery.TtlScheduler;
import org.springframework.cloud.consul.serviceregistry.ConsulRegistration;
import org.springframework.cloud.consul.serviceregistry.ConsulServiceRegistry;

public class ServiceRegistry extends ConsulServiceRegistry {

    private final RouteService routeService;
    private final String appName;
    public ServiceRegistry(ConsulClient client, ConsulDiscoveryProperties properties, TtlScheduler ttlScheduler, HeartbeatProperties heartbeatProperties, String appName, RouteService routeService) {
        super(client, properties, ttlScheduler, heartbeatProperties);
        this.routeService = routeService;
        this.appName = appName;
    }

    @Override
    public void register(ConsulRegistration reg) {
        var svc = reg.getService();

        var tags = new LinkedList<String>();
        tags.add("traefik.enable=true");
//        tags.add(String.format("traefik.http.routers.%s.entrypoints=web", name));
//        tags.add(String.format("traefik.http.routers.%s.rule=PathPrefix(`/api/v?1/stations`) && Method(`GET`)", this.appName));
        routeService.getRoutes().forEach(route -> {
            tags.add(route.getTraefikRule());
        });
        svc.setTags(tags);
        svc.setName(this.appName);
        super.register(reg);
    }
}
