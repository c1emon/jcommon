package icu.clemon.jcommon.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@AutoConfiguration
public class JcommonAutoConfiguration {

    @Bean
    public JcommonConfig jcommonConfig() {
        return new JcommonConfig();
    }
}
