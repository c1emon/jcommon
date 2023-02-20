package icu.clemon.common.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import icu.clemon.common.types.Enumerator;

import java.io.IOException;

public class EnumeratorDeserializer extends JsonDeserializer<Enumerator> {
    @Override
    public Enumerator deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return null;
    }
}
