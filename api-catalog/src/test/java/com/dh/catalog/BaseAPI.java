package com.dh.catalog;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import org.junit.jupiter.api.BeforeAll;

import java.lang.module.Configuration;

import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;

public class BaseAPI {
    protected static Configuration configuration;

    @BeforeAll
    public static void beforeAllTests(){
        configuration = ConfigurationManager.getConfiguration();
        baseURI = configuration.baseURI();
        basePath = configuration.basePath();
        port = configuration.port();

        config = newConfig().
                jsonConfig(jsonConfig().numberReturnType.BIG.DECIMAL)).
                sslConfig(new SSLConfig().allowAllHostnames());

        RestAssured.useRelaxedHTTPSValidation();
    }
}
