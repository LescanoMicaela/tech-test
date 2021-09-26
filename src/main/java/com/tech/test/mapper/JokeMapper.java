package com.tech.test.mapper;

import com.tech.test.exception.ResourceNotFoundException;
import com.tech.test.model.Joke;
import com.tech.test.model.dto.JokeDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * This class maps Joke Entity
 * to JokeDTO
 */
@Component
public class JokeMapper {

    /**
     * Method to map Joke entity to
     * JokeDto
     *
     * @param joke joke entity
     * @return JokeDTO
     */
    public JokeDTO jokeToJokeDTO(Joke joke) {
        return JokeDTO.builder()
                .text(Optional.ofNullable(joke.getText())
                        .orElseThrow(() -> new ResourceNotFoundException("No text found")))
                .id(Optional.ofNullable(joke.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("No id found")))
                .build();
    }

    /**
     * Method to map JokeDto to
     * joke entity
     *
     * @param joke joke data transfer object
     * @return Joke entity
     */
    public Joke jokeDTOToJoke(JokeDTO joke) {
        return Joke.builder()
                .text(Optional.ofNullable(joke.getText())
                        .orElseThrow(() -> new ResourceNotFoundException("No text found")))
                .id(Optional.ofNullable(joke.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("No id found")))
                .build();
    }
}
