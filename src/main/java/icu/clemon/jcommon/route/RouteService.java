package icu.clemon.jcommon.route;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

@RequiredArgsConstructor
public class RouteService {
    private final WebApplicationContext webApplicationContext;

    public List<Route> getRoutes() {
        var routes = new LinkedList<Route>();
        //  WebMvcEndpointHandlerMapping: 将请求映射到 Spring MVC 控制器方法，用于 Spring Actuator 的端点，如 /actuator/health 和 /actuator/info 等；之所以有这个映射是因为项目中使用了 actuator 模块 (spring-boot-starter-actuator);
        //  ControllerEndpointHandlerMapping: 同上，也是 actuator 中的;
        //  RequestMappingHandlerMapping: 查找使用 @Controller 修饰的类中的 @RequestMapping 方法，返回方法级别的映射;
        //  SimpleUrlHandlerMapping: 将 URL 映射到 Bean 实例或者 Bean 的名称;
        //  BeanNameUrlHandlerMapping: 将 URL 映射到以 / 开头的 bean，类似于 Struts 将 URL 映射到 action;
        var mapping = webApplicationContext.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
        var methods = mapping.getHandlerMethods();
        for (var method : methods.entrySet()) {
            var info = method.getKey();

            var patternsCondition = info.getPathPatternsCondition();
            var methodsCondition = info.getMethodsCondition();

            if (patternsCondition != null) {
                var route = new Route();
                var patterns = patternsCondition.getPatterns();

                route.setPatterns(
                        patterns.stream()
                                .map(PathPattern::getPatternString)
                                .filter(p -> !"/error".equals(p))
                                .collect(Collectors.toSet())
                );
                if (!route.getPatterns().isEmpty()) {
                    route.setClassName(method.getValue().getMethod().getDeclaringClass().getSimpleName());
                    route.setMethodName(method.getValue().getMethod().getName());
                    route.setMethods(methodsCondition.getMethods());
                    routes.add(route);
                }

            }
        }
        return routes;
    }

}
