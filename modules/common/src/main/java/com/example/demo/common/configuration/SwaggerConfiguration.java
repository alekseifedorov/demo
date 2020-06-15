package com.example.demo.common.configuration;

import com.example.demo.api.ApiBasePackage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
@Configuration
@SwaggerProfile
public class SwaggerConfiguration {

    @Bean
    @ConditionalOnMissingBean(Docket.class)
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage(ApiBasePackage.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build();
    }
}
