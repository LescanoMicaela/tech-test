package com.tech.test.repository;

import com.tech.test.model.Joke;

import java.util.Optional;

public interface IJokeRepository {

    Optional<Joke> findJokeById(String id);
}
