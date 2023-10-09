package icu.clemon.jcommon.config;

import icu.clemon.jcommon.json.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = JcommonConfig.PREFIX, ignoreInvalidFields = true)
@Data
public class JcommonConfig {

    public static final String PREFIX = "jcommon";

    /**
     * Enumerator config
     */
    @NestedConfigurationProperty
    private Config enumerator;
}
