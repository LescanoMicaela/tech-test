package com.tech.test.service.impl;

import com.tech.test.model.dto.JokeDTO;
import com.tech.test.service.IJokeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JokeServiceImpl implements IJokeService {
    @Override
    public JokeDTO getRandomJoke() {
        return null;
    }
}
