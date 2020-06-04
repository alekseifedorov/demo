package com.example.demo.book;

import com.example.demo.common.TestOracleContainer;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("tc")
@ContextConfiguration(initializers = {UserRepositoryTCLiveTest.Initializer.class})
public class UserRepositoryTCLiveTest  {

    @ClassRule
    public static TestOracleContainer oracleContainer = TestOracleContainer.getInstance();

    @Test
    @Transactional
    public void givenUsersInDB_WhenUpdateStatusForNameModifyingQueryAnnotationNative_ThenModifyMatchingUsers() {

    }

    static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
              "spring.datasource.url=" + oracleContainer.getJdbcUrl(),
              "spring.datasource.username=" + oracleContainer.getUsername(),
              "spring.datasource.password=" + oracleContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
