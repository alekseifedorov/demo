package com.example.demo.book.configuration;

import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@ConfigurationProperties(prefix = "com.example.demo.book")
public class BookFlywayProperties extends FlywayProperties {

}
