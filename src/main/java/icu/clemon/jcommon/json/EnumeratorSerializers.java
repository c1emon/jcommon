package icu.clemon.jcommon.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import icu.clemon.jcommon.exception.APIException;
import icu.clemon.jcommon.types.Enumerator;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static icu.clemon.jcommon.http.ResultCode.CODEIllegalArgument;

public class EnumeratorSerializers {

    public static class Serializer extends JsonSerializer<Enumerator> {
        @Override
        public void serialize(Enumerator value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//            gen.writeStartObject();
//            gen.writeNumberField("id", value.getId());
//            gen.writeStringField("description", value.getDescription());
//            gen.writeEndObject();
            gen.writeNumber(value.getId());
        }
    }

    public static class Deserializer extends JsonDeserializer<Enumerator> implements ContextualDeserializer {

        private final Class<? extends Enumerator> propertyClass;

        public Deserializer() {
            this.propertyClass = null;
        }

        public Deserializer(Class<? extends Enumerator> propertyClass) {
            this.propertyClass = propertyClass;
        }

        public Enumerator getTargetEnumerator(int id) {
            assert propertyClass != null;
            return Arrays.stream(propertyClass.getEnumConstants()) // 调用Class的这个方法，获取枚举类的所有枚举值
                    .filter(e -> e.getId() == id)
                    .findAny()
                    .orElseThrow(() -> new APIException(CODEIllegalArgument, "bad id for " + propertyClass.getSimpleName()));
        }

        @Override
        public Enumerator deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JacksonException {
            assert propertyClass != null;
            AtomicInteger id = new AtomicInteger();

            try {
                id.set(p.getIntValue());
            } catch (IOException ex) {
                try {
                    id.set(Integer.parseInt(p.getText()));
                } catch (IOException | NumberFormatException exx) {
                    throw new APIException(CODEIllegalArgument, "bad id for " + propertyClass.getSimpleName());
                }
            }

            return getTargetEnumerator(id.get());
        }

        @Override
        public JsonDeserializer<Enumerator> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
            var rawClass = property.getType().getRawClass();
            if (Enumerator.class.isAssignableFrom(rawClass) && Enum.class.isAssignableFrom(rawClass)) {
                return new EnumeratorSerializers.Deserializer(rawClass.asSubclass(Enumerator.class));
            }
            return null;
        }
    }

}
