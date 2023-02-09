package icu.clemon.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class CommonJackson2ObjectMapperBuilderCustomizer implements Jackson2ObjectMapperBuilderCustomizer {

    public static String dateFormat = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);
        jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
        jacksonObjectMapperBuilder.failOnUnknownProperties(false);
        jacksonObjectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        jacksonObjectMapperBuilder.simpleDateFormat(dateFormat);

        // 针对于JDK新时间类。序列化时带有T的问题，自定义格式化字符串
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateFormat)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateFormat)));
        jacksonObjectMapperBuilder.modules(javaTimeModule);

        // 默认禁用，禁用情况下，需考虑WRITE_ENUMS_USING_TO_STRING配置。启用后，ENUM序列化为数字
        jacksonObjectMapperBuilder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_INDEX);

        // 仅当WRITE_ENUMS_USING_INDEX为禁用时(默认禁用)，该配置生效
        // 默认关闭，枚举类型序列化方式，默认情况下使用Enum.name()。开启后，使用Enum.toString()。注：需重写Enum的toString方法;
        jacksonObjectMapperBuilder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);

        // 默认关闭，将char[]数组序列化为String类型。若开启后序列化为JSON数组。
        jacksonObjectMapperBuilder.featuresToEnable(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS);

        // 默认开启：如果一个类没有public的方法或属性时，会导致序列化失败。关闭后，会得到一个空JSON串。
        jacksonObjectMapperBuilder.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // 默认关闭，当JSON字段为""(EMPTY_STRING)时，解析为普通的POJO对象抛出异常。开启后，该POJO的属性值为null。
        jacksonObjectMapperBuilder.featuresToEnable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        // 默认关闭
        // jacksonObjectMapperBuilder.featuresToEnable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        // 默认关闭，若POJO中不含有JSON中的属性，则抛出异常。开启后，不解析该字段，而不会抛出异常。
        jacksonObjectMapperBuilder.featuresToEnable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    }

}