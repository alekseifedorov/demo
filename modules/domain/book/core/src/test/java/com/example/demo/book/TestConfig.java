package com.example.demo.book;

import com.example.demo.book.configuration.BookFlywayConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration(exclude = {
        FlywayAutoConfiguration.class,
})
@Import(
        BookFlywayConfiguration.class
)
@ComponentScan(basePackageClasses = BookBasePackage.class)
public class TestConfig {

}
