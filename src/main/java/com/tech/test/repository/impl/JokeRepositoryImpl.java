package com.tech.test.repository.impl;

import com.tech.test.model.Joke;
import com.tech.test.repository.IJokeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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

    /**
     * The redis entity
     */
    private static final String REDIS_ENTITY = "jokes";

    /**
     * The redis template
     */
    private final RedisTemplate<String, Joke> redisTemplate;

    /**
     * Hash operations
     */
    private HashOperations<String, Integer, Joke> hashOperations;


    /**
     * Method init for repository
     */
    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    /**
     * Method to get from cache a joke with a id
     *
     * @param id joke id
     * @return Joke
     */
    @Override
    @Cacheable(value = "jokes", key = "{#id}")
    public Optional<Joke> findJokeById(String id) {
        return Optional.ofNullable(hashOperations.get(REDIS_ENTITY, id));
    }
}
