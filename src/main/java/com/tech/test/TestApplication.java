package com.tech.test;

import com.tech.test.model.Joke;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.web.client.RestTemplate;

@EnableRedisRepositories
@RequiredArgsConstructor
@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(ClientConfigProperties.class)
public class TestApplication {

    private final ClientConfigProperties clientConfigProperties;

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    /**
     * Bean for rest template
     *
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Bean for jedis connection factory
     *
     * @return JedisConnectionFactory
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(clientConfigProperties.getRedisServer(), clientConfigProperties.getRedisPort());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(clientConfigProperties.getPass()));
        return new JedisConnectionFactory(redisStandaloneConfiguration);

    }

    /**
     * Bean for redis template
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Joke> redisTemplate() {
        RedisTemplate<String, Joke> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }


}
