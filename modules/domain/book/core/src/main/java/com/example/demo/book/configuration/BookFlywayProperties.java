package com.example.demo.book.configuration;

import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.example.demo.book")
public class BookFlywayProperties extends FlywayProperties {

}
