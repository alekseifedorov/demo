package com.example.demo.common.configuration;

import com.example.demo.common.CommonBasePackage;
import com.example.demo.common.validation.AnnotationValidator;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses = {CommonBasePackage.class, AnnotationValidator.class})
public class CommonServicesConfiguration {

}
