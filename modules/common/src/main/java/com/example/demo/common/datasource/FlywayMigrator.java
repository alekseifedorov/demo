package com.example.demo.common.datasource;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import javax.annotation.PostConstruct;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class FlywayMigrator {

    public static final Logger log = LoggerFactory.getLogger(FlywayMigrator.class);

    public static final String MIGRATION_PROFILE_NAME = "migration";

    private final Environment environment;
    private final MigrationProperties migrationProperties;
    private final Flyway flyway;

    public FlywayMigrator(MigrationProperties migrationProperties,
                          Environment environment,
                          Flyway flyway) {
        this.environment = environment;
        this.migrationProperties = migrationProperties;
        this.flyway = flyway;
    }

    @PostConstruct
    public void init() {
        log.info("Migrator started. Properties are: [profiles={}, enabled={}].", environment.getActiveProfiles(), migrationProperties.isEnabled());
        if (environment.acceptsProfiles(Profiles.of(MIGRATION_PROFILE_NAME)) /*&& migrationProperties.isEnabled()*/) {
            log.info("Running migration.");
            flyway.migrate();
        } else {
            log.info("Migration disabled.");
        }
    }

}
