package icu.clemon.common.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;
import icu.clemon.common.exception.APIException;
import icu.clemon.common.types.Enumerator;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static icu.clemon.common.http.ResultCode.CODEIllegalArgument;

public class EnumeratorSerializers {

    public static class Serializer extends JsonSerializer<Enumerator> {
        @Override
        public void serialize(Enumerator value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartObject();
            gen.writeNumberField("id", value.getId());
            gen.writeStringField("description", value.getDescription());
            gen.writeEndObject();
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
            return Arrays.stream(propertyClass.getEnumConstants()) // 调用Class的这个方法，获取枚举类的所有枚举值
                    .filter(e -> e.getId() == id)
                    .findAny()
                    .orElseThrow(() -> new APIException(CODEIllegalArgument, "bad id for " + propertyClass.getSimpleName()));
        }

        @Override
        public Enumerator deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JacksonException {

            AtomicInteger id = new AtomicInteger();

            try {
                id.set(p.getIntValue());
            } catch (IOException e1) {
                try {
                    id.set(Integer.parseInt(p.getText()));
                } catch (IOException | NumberFormatException e2) {
                    try {
                        TreeNode treeNode = p.getCodec().readTree(p);
                        treeNode.fieldNames().forEachRemaining(fieldName -> {
                            if (Objects.equals(fieldName, "id")) {
                                try {
                                    id.set(((IntNode) treeNode.get("id")).asInt());
                                } catch (Exception e) {
                                    try {
                                        id.set(Integer.parseInt(((TextNode) treeNode.get("id")).asText()));
                                    } catch (Exception ex) {
                                        throw new APIException(CODEIllegalArgument, "bad id for " + propertyClass.getSimpleName());
                                    }
                                }
                            }
                        });

                    } catch (IOException e3) {
                        throw new IllegalArgumentException("No such id of " + propertyClass.getSimpleName());
                    }
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
