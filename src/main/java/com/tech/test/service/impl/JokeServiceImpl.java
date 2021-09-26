package com.tech.test.service.impl;

import com.tech.test.exception.ResourceNotFoundException;
import com.tech.test.mapper.JokeMapper;
import com.tech.test.model.Joke;
import com.tech.test.model.dto.JokeDTO;
import com.tech.test.repository.IJokeRepository;
import com.tech.test.service.IJokeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
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
     * Repository injection
     */
    private final IJokeRepository repo;

    /**
     * Mapper injection
     */
    private final JokeMapper mapper;

    /**
     * Cache Manager injection
     */
    private final CacheManager cacheManager;

    /**
     * Constructor
     *
     * @param randomJokeEndpoint Random jokes endpoint
     */
    public JokeServiceImpl(@Value("${chuck-api.endpoint.random}") String randomJokeEndpoint,
                           RestTemplate rest,
                           IJokeRepository repo,
                           JokeMapper mapper,
                           CacheManager cacheManager) {
        this.randomJokeEndpoint = randomJokeEndpoint;
        this.rest = rest;
        this.repo = repo;
        this.mapper = mapper;
        this.cacheManager = cacheManager;
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
        log.info("API call {}", randomJokeEndpoint);
        var joke = Optional.ofNullable(rest.getForObject(randomJokeEndpoint, JokeDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("No joke found"));
        loadCache(mapper.jokeDTOToJoke(joke));
        return joke;
    }

    /**
     * Method that search joke from cache
     * by id joke
     *
     * @param id joke id
     * @return JokeDTO
     */
    @Override
    @Cacheable(value = "jokes", key = "{#id}")
    public JokeDTO getJokeById(String id) {
        log.info("JokeServiceImpl.getJokeById()");
        log.info("Searching joke from cache");
        return mapper.jokeToJokeDTO(
                repo.findJokeById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("No joke found in cache with this id")));
    }


    /**
     * This method will load the cache
     *
     * @param joke the joke
     */
    @Override
    public void loadCache(Joke joke) {
        Optional.of(cacheManager).ifPresent(c -> c.getCache("jokes").put(joke.getId(), joke));

    }
}
