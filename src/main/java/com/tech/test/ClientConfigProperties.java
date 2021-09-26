package com.tech.test;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Class from redis client properties
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "client")
public class ClientConfigProperties {

    /**
     * Redis server
     */
    private String redisServer;

    /**
     * Redis port
     */
    private int redisPort;

    /**
     * Redis password
     */
    private String pass;
}