package icu.clemon.jcommon.types;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import icu.clemon.jcommon.json.EnumeratorSerializers;

@JsonDeserialize(using = EnumeratorSerializers.Deserializer.class)
@JsonSerialize(using = EnumeratorSerializers.Serializer.class)
public interface Enumerator {
    Integer getId();
    String getDescription();
}
