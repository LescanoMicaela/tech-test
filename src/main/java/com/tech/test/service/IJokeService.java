package com.tech.test.service;

import com.tech.test.model.Joke;
import com.tech.test.model.dto.JokeDTO;

/**
 * This class will call
 * ChuckNorris API to get joke information
 */
public interface IJokeService {

    /**
     * This method will call ChuckNorris
     * /jokes/random endpoint and
     * will return a JokeDTO
     *
     * @return JokeDTO
     */
    JokeDTO getRandomJoke();

    /**
     * This method will get a
     * joke by id from the cache
     *
     * @param id joke id
     * @return JokeDTO
     */
    JokeDTO getJokeById(String id);

    /**
     * This method will load
     * the cache
     *
     * @param joke the joke
     */
    void loadCache(Joke joke);
}
