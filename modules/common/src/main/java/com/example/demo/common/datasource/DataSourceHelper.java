package com.example.demo.common.datasource;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DataSourceHelper {

    public static Flyway defaultFlyway(FlywayProperties flywayProperties, DataSource dataSource) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setLocations(flywayProperties.getLocations().toArray(new String[0]));
        flyway.setSchemas(flywayProperties.getSchemas().toArray(new String[]{}));

        return flyway;
    }

    public static Map<String, Object> defaultJpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        props.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        return props;
    }

    public static EntityManagerFactoryBuilder defaultEntityManagerFactoryBuilder(JpaProperties properties, DataSource dataSource) {

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        adapter.setShowSql(properties.isShowSql());
        adapter.setDatabase(properties.getDatabase());
        adapter.setDatabasePlatform(properties.getDatabasePlatform());
        adapter.setGenerateDdl(properties.isGenerateDdl());

        return new EntityManagerFactoryBuilder(adapter, Collections.emptyMap(), null);
    }
}
