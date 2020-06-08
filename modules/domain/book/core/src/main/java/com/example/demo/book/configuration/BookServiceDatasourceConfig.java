package com.example.demo.book.configuration;


import com.example.demo.book.BookBasePackage;
import com.example.demo.common.datasource.DataSourceHelper;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Profile("!test")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.demo.book.repository",
        entityManagerFactoryRef = BookServiceDatasourceConfig.ENTITY_MANAGER_FACTORY_NAME,
        transactionManagerRef = BookServiceDatasourceConfig.TRANSACTION_MANAGER_NAME)
@EnableConfigurationProperties({BookFlywayProperties.class, JpaProperties.class})
@PropertySource({"classpath:book.properties", "classpath:application.properties"})
public class BookServiceDatasourceConfig {

    public static final String BASE_PACKAGE = BookBasePackage.class.getPackage().getName();
    public static final String DATASOURCE_NAME = "bookDataSource";
    public static final String ENTITY_MANAGER_FACTORY_NAME = "bookEntityManagerFactory";
    public static final String TRANSACTION_MANAGER_NAME = "bookTransactionManager";
    public static final String PERSISTENCE_UNIT_NAME = "bookPersistenceUnit";
    public static final String FLYWAY_BEAN_NAME = "bookFlyway";
    public static final String DATASOURCE_PREFIX = "com.example.demo.book.datasource";
    public static final String ENTITY_MANAGER_FACTORY_BUILDER_NAME = "bookEntityManagerFactoryBuilder";

    @Bean(name = DATASOURCE_NAME)
    @ConfigurationProperties(prefix = DATASOURCE_PREFIX)
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = ENTITY_MANAGER_FACTORY_NAME)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier(ENTITY_MANAGER_FACTORY_BUILDER_NAME) EntityManagerFactoryBuilder builder,
            @Qualifier(DATASOURCE_NAME) DataSource dataSource
    ) {
        return builder.dataSource(dataSource)
                      .packages(BASE_PACKAGE)
                      .persistenceUnit(PERSISTENCE_UNIT_NAME)
                      .properties(DataSourceHelper.defaultJpaProperties())
                      .build();
    }

    @Bean(name = TRANSACTION_MANAGER_NAME)
    public PlatformTransactionManager transactionManager(
            @Qualifier(ENTITY_MANAGER_FACTORY_NAME) EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean(name = ENTITY_MANAGER_FACTORY_BUILDER_NAME)
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(
            JpaProperties properties,
            @Qualifier(DATASOURCE_NAME) DataSource dataSource
    ) {
        return DataSourceHelper.defaultEntityManagerFactoryBuilder(properties, dataSource);
    }
}
