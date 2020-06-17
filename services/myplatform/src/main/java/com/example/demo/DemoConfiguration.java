package com.example.demo;


import com.example.demo.api.composite.book.configuration.CompositeServiceConfiguration;
import com.example.demo.book.configuration.BookConfiguration;
import com.example.demo.book.configuration.BookFlywayConfiguration;
import com.example.demo.common.configuration.CommonServicesConfiguration;
import com.example.demo.security.config.*;
import org.springframework.context.annotation.Import;

@Import({
        CompositeServiceConfiguration.class,
        BookConfiguration.class,
        BookFlywayConfiguration.class,
        CommonServicesConfiguration.class,
        OAuth2ResourceServerSecurityConfiguration.class
})
public class DemoConfiguration {
}
