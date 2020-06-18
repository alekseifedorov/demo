package com.example.demo.common.banner;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class BannerPrinter {

    private final Banner banner;

    public void print(Environment env, Class<?> sourceClass, Logger log) {
        try {
            if (banner != null) {
                log.info(createStringFromBanner(banner, env, sourceClass));
            }
        } catch (Exception e) {
            log.warn("Failed to create String for banner", e);
        }
    }

    private static String createStringFromBanner(Banner banner, Environment env, Class<?> sourceClass) throws IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            banner.printBanner(env, sourceClass, new PrintStream(out));
            return out.toString(env.getProperty("banner.charset", StandardCharsets.UTF_8.name()));
        }
    }
}
