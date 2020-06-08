package com.example.demo.book.configuration;

import com.example.demo.book.BookBasePackage;
import com.example.demo.common.CommonServicesConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration(exclude = {
        FlywayAutoConfiguration.class,
})
@Import(
        {BookFlywayConfiguration.class, CommonServicesConfiguration.class}
)
@ComponentScan(basePackageClasses = BookBasePackage.class)
public class TestConfig {

}
