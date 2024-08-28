package icu.clemon.jcommon.http;

import com.google.common.base.CaseFormat;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
public class HttpGetSnakeCaseConverter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getMethod().equalsIgnoreCase(RequestMethod.GET.name());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final Map<String, String[]> formattedParams = new ConcurrentHashMap<>();

        for (String param : request.getParameterMap().keySet()) {
            String formattedParam = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, param);
            formattedParams.put(formattedParam, request.getParameterValues(param));
        }

        filterChain.doFilter(new HttpServletRequestWrapper(request) {
            @Override
            public String getParameter(String name) {
                return formattedParams.containsKey(name) ? formattedParams.get(name)[0] : null;
            }

            @Override
            public Enumeration<String> getParameterNames() {
                return Collections.enumeration(formattedParams.keySet());
            }

            @Override
            public String[] getParameterValues(String name) {
                return formattedParams.get(name);
            }

            @Override
            public Map<String, String[]> getParameterMap() {
                return formattedParams;
            }
        }, response);
    }

}
