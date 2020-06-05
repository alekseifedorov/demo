package com.example.demo.book.configuration;

import org.springframework.context.annotation.*;

@Profile("!test")
@Configuration
@ComponentScan
@Import({BookServiceDatasourceConfig.class, BookFlywayConfiguration.class})
@PropertySource({"classpath:book.properties"})
public class BookConfiguration {
}
