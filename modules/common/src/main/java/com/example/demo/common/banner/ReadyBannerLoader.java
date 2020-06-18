package com.example.demo.common.banner;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.ResourceBanner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadyBannerLoader {

    private static final String BANNER_LOCATION_PROPERTY = "banner.ready.location";
    private static final String DEFAULT_BANNER_LOCATION = "ready-banner.txt";

    public static ResourceBanner create(ApplicationContext context) {
        Environment env = context.getEnvironment();
        String location = "classpath:" + env.getProperty(BANNER_LOCATION_PROPERTY, DEFAULT_BANNER_LOCATION);
        location = location.startsWith("classpath:") ? location : ("classpath:" + location);
        Resource resource = context.getResource(location);
        return resource.exists() ? new ResourceBanner(resource) : null;
    }
}
