package com.example.demo.book.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.example.demo.book.entity.AuthorEntity;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

import static com.example.demo.book.configuration.BookServiceDatasourceConfig.PERSISTENCE_UNIT_NAME;


@Configuration
@Profile("test")
@EnableJpaRepositories(basePackages = "com.example.demo.book.repository", entityManagerFactoryRef = "h2EntityManagerFactory", transactionManagerRef = "bookTransactionManager")
@EnableTransactionManagement
public class H2Configuration {

    @Bean("h2DataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false;MODE=Oracle");
        // schema init
        Resource initSchema = new ClassPathResource("sql/oracle/V1.0__init.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);

        return dataSource;
    }

    @Bean("h2JpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setGenerateDdl(false);
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setDatabase(Database.H2);
        return hibernateJpaVendorAdapter;
    }

    @Bean("h2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("h2DataSource") DataSource dataSource,
                                                                       @Qualifier("h2JpaVendorAdapter") JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setPackagesToScan(AuthorEntity.class.getPackage().getName());
        return entityManagerFactory;
    }

    @Bean("h2JpaDialect")
    public JpaDialect jpaDialect() {
        return new HibernateJpaDialect();
    }

    @Bean("bookTransactionManager")
    public JpaTransactionManager transactionManager(
            @Qualifier("h2EntityManagerFactory") EntityManagerFactory entityManagerFactory,
            @Qualifier("h2JpaDialect") JpaDialect jpaDialect) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        jpaTransactionManager.setJpaDialect(jpaDialect);
        return jpaTransactionManager;
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2WebServer() throws SQLException {
        return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "9001");
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    @DependsOn("h2WebServer")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }

}
