package icu.clemon.jcommon.mongodb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class MongoAutoConfiguration {

  @Bean
  public MappingMongoConverter mappingMongoConverter(
      MongoDatabaseFactory factory, MongoMappingContext context, ObjectMapper mapper) {
    DbRefResolver resolver = new DefaultDbRefResolver(factory);
    MappingMongoConverter converter = new MappingMongoConverter(resolver, context);
    converter.setTypeMapper(new DefaultMongoTypeMapper(null));

    return converter;
  }
}
