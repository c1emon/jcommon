package icu.clemon.jcommon.http;

import icu.clemon.jcommon.exception.APIException;
import icu.clemon.jcommon.types.Enumerator;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static icu.clemon.jcommon.http.ResultCode.CODEIllegalArgument;

// for mvc param convert
public class EnumeratorConverter implements ConditionalGenericConverter {

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return Enumerator.class.isAssignableFrom(targetType.getType());
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        var s = new HashSet<ConvertiblePair>();
        s.add(new ConvertiblePair(String.class, Enumerator.class));
        s.add(new ConvertiblePair(int.class, Enumerator.class));
        return s;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        assert source != null;
        AtomicInteger id = new AtomicInteger();

        try {
            id.set(Integer.parseInt(source.toString()));
        } catch (NumberFormatException e) {
            throw new APIException(CODEIllegalArgument,
                    String.format("failed convert value %s to type %s", source, targetType.getName()));
        }

        return Arrays.stream(targetType.getType().asSubclass(Enumerator.class).getEnumConstants()) // 调用Class的这个方法，获取枚举类的所有枚举值
                .filter(e -> e.getId() == id.get())
                .findAny()
                .orElseThrow(() -> new APIException(CODEIllegalArgument,
                        String.format("failed convert value %s to type %s", source, targetType.getName())));

    }
}
