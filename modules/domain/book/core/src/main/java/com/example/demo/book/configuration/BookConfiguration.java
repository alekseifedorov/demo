package com.example.demo.book.configuration;

import com.example.demo.book.BookBasePackage;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackageClasses = BookBasePackage.class)
@Import({BookServiceDatasourceConfig.class, BookFlywayConfiguration.class})
@PropertySource({"classpath:book.properties"})
public class BookConfiguration {
}
