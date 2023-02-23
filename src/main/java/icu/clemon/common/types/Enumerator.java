package icu.clemon.common.types;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import icu.clemon.common.json.EnumeratorDeserializer;
import icu.clemon.common.json.EnumeratorSerializer;

@JsonDeserialize(using = EnumeratorDeserializer.class)
@JsonSerialize(using = EnumeratorSerializer.class)
public interface Enumerator {
    Integer getId();
    String getDescription();
}
