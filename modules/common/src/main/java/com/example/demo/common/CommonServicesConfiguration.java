package com.example.demo.common;

import com.example.demo.common.validation.AnnotationValidator;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses = {CommonBasePackage.class, AnnotationValidator.class})
public class CommonServicesConfiguration {
}
