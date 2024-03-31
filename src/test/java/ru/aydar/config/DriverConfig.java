package ru.aydar.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/driver.properties"
})
public interface DriverConfig extends Config {
    String userName();
    String accessKey();
    String app();
    String device();
    String osVersion();
    String project();
    String build();
    String testName();
}
