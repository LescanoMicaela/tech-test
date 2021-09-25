package com.tech.test.service.impl;

import com.tech.test.exception.ResourceNotFoundException;
import com.tech.test.model.dto.JokeDTO;
import com.tech.test.service.IJokeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Joke Service, make api calls
 * to Cuck Norris API
 */
@Slf4j
@Service
public class JokeServiceImpl implements IJokeService {

    /**
     * Random jokes endpoint
     */
    private final String randomJokeEndpoint;

    /**
     * RestTemplate injection
     */
    private final RestTemplate rest;

    /**
     * Constructor
     *
     * @param randomJokeEndpoint Random jokes endpoint
     */
    public JokeServiceImpl(@Value("${chuck-api.endpoint.random}") String randomJokeEndpoint, RestTemplate rest) {
        this.randomJokeEndpoint = randomJokeEndpoint;
        this.rest = rest;
    }

    /**
     * Gets a random joke from
     * Chuck Norris API
     *
     * @return JokeDTO
     */
    @Override
    public JokeDTO getRandomJoke() {
        log.info("JokeServiceImpl.getRandomJoke()");
        log.info("API call {}",randomJokeEndpoint);
        return Optional.ofNullable(rest.getForObject(randomJokeEndpoint, JokeDTO.class))
                .orElseThrow( () -> new ResourceNotFoundException("No joke found"));
    }
}
