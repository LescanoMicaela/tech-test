package com.tech.test.repository;

import com.tech.test.model.Joke;

import java.util.Optional;

/**
 * Interface of joke repository
 */
public interface IJokeRepository {

    /**
     * Finds a joke by id
     *
     * @param id joke id
     * @return Joke
     */
    Optional<Joke> findJokeById(String id);
}
