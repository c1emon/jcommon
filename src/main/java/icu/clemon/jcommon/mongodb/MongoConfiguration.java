package icu.clemon.jcommon.mongodb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import icu.clemon.jcommon.types.Enumerator;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

public class MongoConfiguration extends AbstractMongoClientConfiguration {
    @Override
    protected String getDatabaseName() {
        return null;
    }

    public MongoCustomConversions mongoCustomConversions() {
        List<Object> converters = new ArrayList<>();

        return new MongoCustomConversions(converters);
    }

    @AllArgsConstructor
    static public class EnumeratorMongoSerializer <T extends Enumerator> implements Converter<T, DBObject> {

        private final ObjectMapper mapper;

        @Override
        public DBObject convert(T source) {
            return BasicDBObject.parse(String.valueOf(source.getId()));
        }
    }

    @AllArgsConstructor
    static public class EnumeratorMongoDeserializer <T extends Enumerator> implements Converter<DBObject, T> {

        private final ObjectMapper mapper;

        @Override
        public T convert(DBObject source) {
//            mapper.readValue()
            return null;
        }
    }
}
