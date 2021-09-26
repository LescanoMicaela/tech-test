package com.tech.test.service.impl;


import com.tech.test.ClientConfigProperties;
import com.tech.test.exception.ResourceNotFoundException;
import com.tech.test.mapper.JokeMapper;
import com.tech.test.model.Joke;
import com.tech.test.model.dto.JokeDTO;
import com.tech.test.repository.IJokeRepository;
import com.tech.test.service.IJokeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JokeServiceImplTest {

    private static final String ENDPOINT = "http://localhost:8080";
    private static final String REDIS_ENTITY = "jokes";
    @Mock
    CacheManager cacheManager;
    @Mock
    Cache cache;
    private JokeDTO joke;
    private Joke jokeEntity;
    private IJokeService service;
    @Mock
    private RestTemplate rest;
    @Mock
    private IJokeRepository repo;
    @Mock
    private JokeMapper mapper;
    @Mock
    private RedisTemplate<String, Joke> redisTemplate;


    private HashOperations<String, Integer, Joke> hashOperations;

    @Before
    public void setUp() {
        service = new JokeServiceImpl(ENDPOINT, rest, repo, mapper, cacheManager);
        joke = JokeDTO.builder()
                .id("id")
                .text("text")
                .build();

        jokeEntity = Joke.builder()
                .id("id")
                .text("text")
                .build();
    }

    @Test
    public void getRandomJokeOk() {

        when(rest.getForObject(ENDPOINT, JokeDTO.class)).thenReturn(joke);
        when(mapper.jokeDTOToJoke(joke)).thenReturn(jokeEntity);
        when(cacheManager.getCache("jokes")).thenReturn(cache);

        var actual = service.getRandomJoke();
        assertNotNull(actual);
        assertEquals(joke.getId(), actual.getId());
        assertEquals(joke.getText(), actual.getText());

        verify(rest, times(1)).getForObject(ENDPOINT, JokeDTO.class);
        verify(mapper, times(1)).jokeDTOToJoke(joke);


    }

    @Test(expected = HttpServerErrorException.class)
    public void getRandomJokeWillThrowHttpServerExceptionTest() {
        when(rest.getForObject(ENDPOINT, JokeDTO.class)).thenThrow(new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE));
        service.getRandomJoke();
    }

    @Test(expected = HttpClientErrorException.class)
    public void getRandomJokeWillThrowHttpClientErrorExceptionTest() {
        when(rest.getForObject(ENDPOINT, JokeDTO.class)).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        service.getRandomJoke();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getRandomJokeWillThrowResourceNotFoundExceptionTest() {
        when(rest.getForObject(ENDPOINT, JokeDTO.class)).thenThrow(new ResourceNotFoundException("Not found"));
        service.getRandomJoke();
    }

    @Configuration
    static class Config {

        // this bean will be injected into the OrderServiceTest class

        @Autowired
        ClientConfigProperties clientConfigProperties;

        @Bean
        public JedisConnectionFactory jedisConnectionFactory() {

            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(clientConfigProperties.getRedisServer(), clientConfigProperties.getRedisPort());
            redisStandaloneConfiguration.setPassword(RedisPassword.of(clientConfigProperties.getPass()));
            return new JedisConnectionFactory(redisStandaloneConfiguration);

        }

        @Bean
        public RedisTemplate<String, Joke> redisTemplate() {
            RedisTemplate<String, Joke> template = new RedisTemplate<>();
            template.setConnectionFactory(jedisConnectionFactory());
            return template;
        }
    }
}
