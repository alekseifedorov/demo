package com.example.demo.book.configuration;

import com.example.demo.common.datasource.DataSourceHelper;
import com.example.demo.common.datasource.FlywayMigrator;
import com.example.demo.common.datasource.MigrationProperties;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

import static com.example.demo.book.configuration.BookServiceDatasourceConfig.DATASOURCE_NAME;

@EnableConfigurationProperties({MigrationProperties.class, BookFlywayProperties.class})
@PropertySource({"classpath:book.properties"})
public class BookFlywayConfiguration {

    public static final String FLYWAY_BEAN_NAME = "bookFlywayConfiguration";
    private static final String SERVICE_MIGRATOR_NAME = "bookServiceMigrator";

    @Bean(name = FLYWAY_BEAN_NAME)
    public Flyway flyway(BookFlywayProperties flywayProperties,
                         @Qualifier(DATASOURCE_NAME) DataSource dataSource
    ) {
        return DataSourceHelper.defaultFlyway(flywayProperties, dataSource);
    }

    @Bean(name = SERVICE_MIGRATOR_NAME)
    public FlywayMigrator migrator(MigrationProperties migrationProperties,
                                   Environment environment,
                                   @Qualifier(FLYWAY_BEAN_NAME) Flyway flyway
    ) {
        return new FlywayMigrator(migrationProperties, environment, flyway);
    }
}
