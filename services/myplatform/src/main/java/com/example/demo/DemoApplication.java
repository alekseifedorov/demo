package com.example.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
		DataSourceAutoConfiguration.class,
		FlywayAutoConfiguration.class
})
@Import(DemoConfiguration.class)
public class DemoApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(DemoApplication.class)
				//.beanNameGenerator(new ConflictResolvingBeanNameGenerator())
				.bannerMode(Banner.Mode.OFF)
				.build()
				.run(args);
	}
}
