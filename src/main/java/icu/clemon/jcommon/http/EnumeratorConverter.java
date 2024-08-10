package icu.clemon.jcommon.http;

import icu.clemon.jcommon.exception.APIException;
import icu.clemon.jcommon.json.EnumeratorSerializers;
import icu.clemon.jcommon.types.Enumerator;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static icu.clemon.jcommon.http.ResultCode.IllegalArgument;

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
            throw new APIException(IllegalArgument,
                    String.format("failed convert value %s to type %s", source, targetType.getName()));
        }

        return EnumeratorSerializers.Deserializer.
                getActualEnumerator(targetType.getType().asSubclass(Enumerator.class),
                                    id.get());
    }
}
