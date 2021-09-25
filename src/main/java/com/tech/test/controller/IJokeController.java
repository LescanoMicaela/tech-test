package com.tech.test.controller;

import com.tech.test.model.dto.JokeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Inteface of JokeControllerImpl
 * This class contains the requests
 */
public interface IJokeController {

    /**
     * Gets random joke
     * No body is required
     * Returns JokeDTO
     *
     * @return ResponseEntity
     */
    @Operation(summary = "Get a random Chuck Norris joke")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found a joke",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JokeDTO.class)) }),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content) })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<JokeDTO> getRandomJoke();
}
