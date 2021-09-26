package com.tech.test.repository.impl;

import com.tech.test.model.Joke;
import com.tech.test.repository.IJokeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * Class repository for Joke
 */
@Repository
@RequiredArgsConstructor
public class JokeRepositoryImpl implements IJokeRepository {

    private static final String REDIS_ENTITY = "jokes";

    private final RedisTemplate<String, Joke> redisTemplate;

    private HashOperations<String, Integer, Joke> hashOperations;


    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Optional<Joke> findJokeById(String id) {
        return Optional.ofNullable(hashOperations.get(REDIS_ENTITY, id));
    }
}
