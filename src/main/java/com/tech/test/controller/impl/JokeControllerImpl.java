package com.tech.test.controller.impl;

import com.tech.test.controller.IJokeController;
import com.tech.test.model.dto.JokeDTO;
import com.tech.test.service.IJokeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implentation of JokeController
 */
@Slf4j
@RestController
@RequestMapping("/v1")
@Tag(name = "Joke Controller", description = "Gets random jokes")
public class JokeControllerImpl implements IJokeController {


    /**
     * JokeService Injection
     */
    private final IJokeService service;

    /**
     * All args constructor
     *
     * @param service JokeService
     */
    public JokeControllerImpl(IJokeService service) {this.service = service;}

    /**
     * @return ResponseEntity
     * <p>
     * 200 - OK
     * 503 - Service Unavailable error
     * </p>
     */
    @Override
    @PostMapping(value = "/joke-request")
    public ResponseEntity<JokeDTO> getRandomJoke() {
        log.info("JokeControllerImpl.getRandomJoke");
        return ResponseEntity.ok(service.getRandomJoke());
    }
}
