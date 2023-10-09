package icu.clemon.jcommon.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = JcommonConfig.PREFIX, ignoreInvalidFields = true)
@Data
public class JcommonConfig {

    public static final String PREFIX = "jcommon";

    /**
     * should deserialize Enumerator with desc
     */
    private boolean withDesc = false;
}
