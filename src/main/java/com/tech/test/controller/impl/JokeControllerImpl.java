package com.tech.test.controller.impl;

import com.tech.test.controller.IJokeController;
import com.tech.test.model.dto.JokeDTO;
import com.tech.test.service.IJokeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public JokeControllerImpl(IJokeService service) {
        this.service = service;
    }

    /**
     * @return ResponseEntity
     * <p>
     * 200 - OK
     * 404 - Not found
     * 503 - Service Unavailable error
     * </p>
     **/
    @Override
    @PostMapping(value = "/joke-request")
    public ResponseEntity<JokeDTO> getRandomJoke() {
        log.info("JokeControllerImpl.getRandomJoke");
        return ResponseEntity.ok(service.getRandomJoke());
    }

    /**
     * @param id the joke id
     * @return ResponseEntity
     * <p>
     * 200 - OK
     * 404 - Not found
     * 503 - Service Unavailable error
     * </p>
     */
    @Override
    @GetMapping(value = "/joke/{id}")
    public ResponseEntity<JokeDTO> getJoke(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.getJokeById(id));
    }
}
