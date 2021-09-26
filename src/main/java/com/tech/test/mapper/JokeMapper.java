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

    public JokeDTO jokeToJokeDTO(Joke joke) {
        return JokeDTO.builder()
                .text(Optional.ofNullable(joke.getText())
                        .orElseThrow(() -> new ResourceNotFoundException("No text found")))
                .id(Optional.ofNullable(joke.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("No id found")))
                .build();
    }

    public Joke jokeDTOToJoke(JokeDTO joke) {
        return Joke.builder()
                .text(Optional.ofNullable(joke.getText())
                        .orElseThrow(() -> new ResourceNotFoundException("No text found")))
                .id(Optional.ofNullable(joke.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("No id found")))
                .build();
    }
}
