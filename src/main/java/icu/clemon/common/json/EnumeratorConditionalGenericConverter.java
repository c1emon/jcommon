package icu.clemon.common.json;

import icu.clemon.common.exception.APIException;
import icu.clemon.common.types.Enumerator;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static icu.clemon.common.http.ResultCode.CODEIllegalArgument;

public class EnumeratorConditionalGenericConverter implements ConditionalGenericConverter {


    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        Class<?> sourceClz = sourceType.getType();
        Class<?> targetClz = targetType.getType();

        if (sourceClz.isAssignableFrom(Enumerator.class)) {
            return targetClz.isAssignableFrom(String.class) || targetClz.isAssignableFrom(Integer.class);
        };
        if (targetClz.isAssignableFrom(Enumerator.class)) {
            return sourceClz.isAssignableFrom(String.class) || sourceClz.isAssignableFrom(Integer.class);
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
        return pairs;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Class<?> sourceClz = sourceType.getType();
        Class<?> targetClz = targetType.getType();

        if (sourceClz.isAssignableFrom(Enumerator.class)) {
            Enumerator enumerator = (Enumerator) source;

            if(targetClz.isAssignableFrom(String.class)) {
                 return "{\"id\": %d}";
             }
             if (targetClz.isAssignableFrom(Integer.class)) {
                return enumerator.getId();
             }
        };
        if (targetClz.isAssignableFrom(Enumerator.class)) {

            Integer id = -100;

            if (sourceClz.isAssignableFrom(String.class)) {
                var sourceStr = (String)source;
                try{
                    Integer.parseInt(sourceStr);
                } catch (Exception e) {

                }

            }
            if (sourceClz.isAssignableFrom(Integer.class)) {
                id = (Integer)source;
            }

            Integer finalId = id;
            Arrays.stream(targetClz.asSubclass(Enumerator.class).getEnumConstants())
                    .filter(e -> Objects.equals(e.getId(), finalId))
                    .findAny()
                    .orElseThrow(() -> new APIException(CODEIllegalArgument, "bad id for " + targetClz.getSimpleName()));
        };

        return null;
    }
}
