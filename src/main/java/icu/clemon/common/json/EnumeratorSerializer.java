package icu.clemon.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import icu.clemon.common.types.Enumerator;

import java.io.IOException;

public class EnumeratorSerializer extends JsonSerializer<Enumerator> {
    @Override
    public void serialize(Enumerator value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

    }
}
