package com.tech.test.service.impl;

import com.tech.test.model.dto.JokeDTO;
import com.tech.test.service.IJokeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public JokeServiceImpl(@Value("${chuck-api.endpoint.random}") String randomJokeEndpoint) {
        this.randomJokeEndpoint = randomJokeEndpoint;
        this.rest = new RestTemplate();
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
        return rest.getForObject(randomJokeEndpoint, JokeDTO.class);
    }
}
