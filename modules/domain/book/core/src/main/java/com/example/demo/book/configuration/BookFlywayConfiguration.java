package com.example.demo.book.configuration;

import com.example.demo.common.DataSourceHelper;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

import static com.example.demo.book.configuration.BookServiceDatasourceConfig.DATASOURCE_NAME;


@EnableConfigurationProperties({BookFlywayProperties.class})
@PropertySource({"classpath:book.properties"})
public class BookFlywayConfiguration {

    public static final String FLYWAY_BEAN_NAME = "bookFlyway";

    @Bean(name = FLYWAY_BEAN_NAME)
    public Flyway flyway(BookFlywayProperties flywayProperties,
                         @Qualifier(DATASOURCE_NAME) DataSource dataSource
    ) {
        return DataSourceHelper.defaultFlyway(flywayProperties, dataSource);
    }
}
