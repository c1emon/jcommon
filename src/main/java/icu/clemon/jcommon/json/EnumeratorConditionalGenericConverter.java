package icu.clemon.jcommon.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import icu.clemon.jcommon.exception.APIException;
import icu.clemon.jcommon.types.Enumerator;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.stereotype.Component;

import java.util.*;

import static icu.clemon.jcommon.http.ResultCode.CODEIllegalArgument;

@Component
@AllArgsConstructor
public class EnumeratorConditionalGenericConverter implements ConditionalGenericConverter {

    private final ObjectMapper objectMapper;
    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        Class<?> sourceClz = sourceType.getType();
        Class<?> targetClz = targetType.getType();

        if (Enumerator.class.isAssignableFrom(sourceClz)) {
            return String.class.isAssignableFrom(targetClz) || Integer.class.isAssignableFrom(targetClz);
        };
        if (Enumerator.class.isAssignableFrom(targetClz)) {
            return String.class.isAssignableFrom(sourceClz) || Integer.class.isAssignableFrom(sourceClz);
        };
        return false;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        HashSet<ConvertiblePair> pairs = new HashSet<>();
        pairs.add(new ConvertiblePair(Enumerator.class, String.class));
        pairs.add(new ConvertiblePair(String.class, Enumerator.class));

        pairs.add(new ConvertiblePair(Integer.class, Integer.class));
        pairs.add(new ConvertiblePair(Integer.class, Enumerator.class));
        return null;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Class<?> sourceClz = sourceType.getType();
        Class<?> targetClz = targetType.getType();

        if (Enumerator.class.isAssignableFrom(sourceClz)) {
            Enumerator enumerator = (Enumerator) source;

            if(String.class.isAssignableFrom(targetClz)) {
                 return enumerator.toJson();
             }
             if (Integer.class.isAssignableFrom(targetClz)) {
                return enumerator.getId();
             }
        };


        if (Enumerator.class.isAssignableFrom(targetClz)) {

            Integer id = -100;

            if (Integer.class.isAssignableFrom(sourceClz)) {
                id = (Integer)source;
            } else if (String.class.isAssignableFrom(sourceClz)) {
                var sourceStr = (String)source;
                try{
                    id = Integer.parseInt(sourceStr);
                } catch (Exception e) {
                    try {
                        Map<String, Object> value = objectMapper.readValue(sourceStr, new TypeReference<Map<String, Object>>() {
                        });

                        id = (Integer) value.get("id");
                    } catch (Exception ex) {
                        throw new APIException(CODEIllegalArgument, "bad id for " + targetClz.getSimpleName());
                    }
                }
            }

            Integer finalId = id;
            return Arrays.stream(targetClz.asSubclass(Enumerator.class).getEnumConstants())
                    .filter(e -> Objects.equals(e.getId(), finalId))
                    .findAny()
                    .orElseThrow(() -> new APIException(CODEIllegalArgument, "bad id for " + targetClz.getSimpleName()));
        };

        return null;
    }

}
