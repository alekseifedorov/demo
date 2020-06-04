package com.example.demo.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "schema.migration")
@Getter
@Setter
public class MigrationProperties {
    private boolean enabled;
}
