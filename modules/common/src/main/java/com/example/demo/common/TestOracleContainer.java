package com.example.demo.common;

import org.testcontainers.containers.OracleContainer;

public class TestOracleContainer extends OracleContainer {

    private static final String IMAGE_VERSION = "webdizz/oracle-xe-11g-sa";

    private static TestOracleContainer container;


    private TestOracleContainer() {
        super(IMAGE_VERSION);
    }

    public static TestOracleContainer getInstance() {
        if (container == null) {
            container = new TestOracleContainer();
            container.withStartupTimeoutSeconds(10000).withPassword()
                    .withEnv("DATABASES", "xyz");
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
