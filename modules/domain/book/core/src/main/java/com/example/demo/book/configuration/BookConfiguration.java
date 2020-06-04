package com.example.demo.book.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@Import({BookServiceDatasourceConfig.class, BookFlywayConfiguration.class})
@PropertySource({"classpath:book.properties"})
public class BookConfiguration {
}
