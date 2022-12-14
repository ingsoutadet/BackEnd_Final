package com.dh.catalog;

import io.restassured.config.Config;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:api.properties"
})
public interface Configuration extends Config{
    @Key("api.base.path")
    String basePath();

    @Key("api.base.uri")
    String baseURI();

    @Key("api.port")
    int port();

    @Key("api.health.context")
    String health();

    @Key("log.all")
    boolean logAll();

}
