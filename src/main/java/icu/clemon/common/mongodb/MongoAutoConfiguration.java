package icu.clemon.common.mongodb;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@AutoConfiguration
public class MongoAutoConfiguration {

    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory factory, MongoMappingContext context) {
        DbRefResolver resolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter converter = new MappingMongoConverter(resolver, context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

}
