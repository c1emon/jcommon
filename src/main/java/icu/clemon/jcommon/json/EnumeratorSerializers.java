package icu.clemon.jcommon.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import icu.clemon.jcommon.config.JcommonConfig;
import icu.clemon.jcommon.exception.APIException;
import icu.clemon.jcommon.types.Enumerator;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static icu.clemon.jcommon.http.ResultCode.CODEIllegalArgument;

public class EnumeratorSerializers {

    @RequiredArgsConstructor
    public static class Serializer extends JsonSerializer<Enumerator> {
        private final JcommonConfig config;
        @Override
        public void serialize(Enumerator value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (config.getEnumerator().isSerializeWithDesc()) {
                gen.writeStartObject();
                gen.writeNumberField("id", value.getId());
                gen.writeStringField("desc", value.getDescription());
                gen.writeEndObject();
            } else {
                gen.writeNumber(value.getId());
            }
        }
    }

    public static class Deserializer extends JsonDeserializer<Enumerator> implements ContextualDeserializer {

        private final Class<? extends Enumerator> propertyClass;

        public Deserializer() {
            super();
            this.propertyClass = null;
        }

        public Deserializer(final Class<? extends Enumerator> propertyClass) {
            super();
            this.propertyClass = propertyClass;
        }

        public static Enumerator getActualEnumerator(Class<? extends Enumerator> clz, int id) {
            return Arrays.stream(clz.getEnumConstants()) // 调用Class的这个方法，获取枚举类的所有枚举值
                    .filter(e -> e.getId() == id)
                    .findAny()
                    .orElseThrow(() -> new APIException(CODEIllegalArgument,
                            String.format("failed convert value %s to type %s", id, clz.getName())));
        }

        @Override
        public Enumerator deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
            assert propertyClass != null;
            AtomicInteger id = new AtomicInteger();

            try {
                id.set(p.getIntValue());
            } catch (IOException ex) {
                try {
                    id.set(Integer.parseInt(p.getText()));
                } catch (IOException | NumberFormatException exx) {
                    throw new APIException(CODEIllegalArgument,
                            String.format("failed convert value %s to type %s", p.getText(), propertyClass.getName()));
                }
            }

            return getActualEnumerator(propertyClass, id.get());
        }

        @Override
        public JsonDeserializer<Enumerator> createContextual(final DeserializationContext ctx, final BeanProperty property) {
            assert property != null;
            var rawClass = property.getType().getRawClass();
            if (Enumerator.class.isAssignableFrom(rawClass) && Enum.class.isAssignableFrom(rawClass)) {
                return new EnumeratorSerializers.Deserializer(rawClass.asSubclass(Enumerator.class));
            }
            return null;
        }
    }

}
