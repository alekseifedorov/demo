package com.example.demo.api.composite.book.configuration;

import com.example.demo.api.composite.book.CompositeServiceBasePackage;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

public class CompositeServiceConfiguration {

    @ComponentScan(basePackageClasses = CompositeServiceBasePackage.class)
    @ServletComponentScan(basePackageClasses = CompositeServiceBasePackage.class)
    public static class Core {
        // nothing
    }
}
