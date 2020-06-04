package com.example.demo;

import com.example.demo.book.configuration.BookConfiguration;
import org.springframework.context.annotation.Import;

@Import({
        BookConfiguration.class,
})
public class DemoConfiguration {
    // Nothing
}
