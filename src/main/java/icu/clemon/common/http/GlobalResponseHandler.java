package icu.clemon.common.http;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        var clz = returnType.getParameterType();
        ResponseWrapper responseWrapperAnno = returnType.getAnnotatedElement().getAnnotation(ResponseWrapper.class);

        var f1 = clz.isAssignableFrom(Result.class);
        var f2 = clz.isAssignableFrom(PageResult.class);
        var f3 = false;
        if (responseWrapperAnno != null ) {
            f3 = !responseWrapperAnno.enabled();
        }
        return !(f1 || f2 || f3);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return Result.success(body);
    }
}
