package com.example.demo.common.configuration;

import com.example.demo.common.CommonBasePackage;
import com.example.demo.common.banner.BannerPrinter;
import com.example.demo.common.banner.ReadyBannerLoader;
import com.example.demo.common.validation.AnnotationValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@Slf4j
@ComponentScan(basePackageClasses = {CommonBasePackage.class, AnnotationValidator.class})
public class CommonServicesConfiguration implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ApplicationContext context = event.getApplicationContext();
        Environment env = context.getEnvironment();
        new BannerPrinter(ReadyBannerLoader.create(context)).print(env, CommonServicesConfiguration.class, log);
    }
}
