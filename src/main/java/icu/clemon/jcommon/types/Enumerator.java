package icu.clemon.jcommon.types;

//@JsonDeserialize(using = EnumeratorSerializers.Deserializer.class)
//@JsonSerialize(using = EnumeratorSerializers.Serializer.class)
public interface Enumerator {
    Integer getId();
    String getDescription();

    default String toJson() {
        return String.format("{\"id\": %d, \"description\": %s}", getId(), getDescription());
    }
}
