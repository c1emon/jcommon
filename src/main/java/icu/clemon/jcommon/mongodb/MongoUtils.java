package icu.clemon.jcommon.mongodb;

import icu.clemon.jcommon.utils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

public class MongoUtils {

  public static Criteria criteriaFuzzy(String field, String p) {
    assert field != null;
    return Criteria.where(field)
        .regex(String.format("^.*%s.*$", StringUtils.isNotBlank(p) ? p : ".*"));
  }

  public static Update updateFrom(Object o) {
    MongoTemplate template = (MongoTemplate) BeanUtils.getBean("mongoTemplate");

    var doc = new Document();
    var update = new Update();
    template.getConverter().write(o, doc);
    doc.forEach(update::set);
    return update;
  }
}
