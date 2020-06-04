package com.example.demo.book.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

//import org.h2.tools.Server;
import com.example.demo.book.entity.Author;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static com.example.demo.book.configuration.BookServiceDatasourceConfig.PERSISTENCE_UNIT_NAME;


//import com.zoetis.ngp.analysis.imputation.stub.repository.RawGenotype;

@Configuration
//@Profile("!test")
@EnableJpaRepositories(basePackages = {"com.example.demo", "com.example.demo.repository"}, entityManagerFactoryRef = "h2EntityManagerFactory", transactionManagerRef = "h2TransactionManager")
@EnableTransactionManagement
public class H2Configuration {

    @Bean("h2DataSource")
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2)
                                     .addScript("sql/h2/V1.0__init.sql").build();
        return db;
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
        entityManagerFactory.setPackagesToScan(Author.class.getPackage().getName());
        return entityManagerFactory;
    }

    @Bean("h2JpaDialect")
    public JpaDialect jpaDialect() {
        return new HibernateJpaDialect();
    }

    @Bean("h2TransactionManager")
    public JpaTransactionManager transactionManager(
            @Qualifier("h2EntityManagerFactory") EntityManagerFactory entityManagerFactory,
            @Qualifier("h2JpaDialect") JpaDialect jpaDialect) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        jpaTransactionManager.setJpaDialect(jpaDialect);
        return jpaTransactionManager;
    }

//    @Bean(initMethod = "start", destroyMethod = "stop")
//    public Server h2WebServer() throws SQLException {
//        return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "9001");
//    }
//
//    @Bean(initMethod = "start", destroyMethod = "stop")
//    @DependsOn("h2WebServer")
//    public Server h2Server() throws SQLException {
//        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
//    }

}
