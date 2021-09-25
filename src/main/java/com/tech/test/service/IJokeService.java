package com.tech.test.service;

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
}
