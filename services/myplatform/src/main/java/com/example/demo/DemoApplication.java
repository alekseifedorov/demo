package com.example.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableAutoConfiguration(exclude = {
		DataSourceAutoConfiguration.class,
		FlywayAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
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
