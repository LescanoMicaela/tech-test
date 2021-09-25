package com.tech.test.model.dto;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class JokeDTOTest {

    @Test
    public void noArgsConstructorTest() {
        JokeDTO jokeDTO = new JokeDTO();
        assertNotNull(jokeDTO);
    }

    @Test
    public void allARgsConstructorTest() {
        JokeDTO jokeDTO = new JokeDTO("id","text");
        assertNotNull(jokeDTO);
        assertNotNull(jokeDTO.getText());
        assertNotNull(jokeDTO.getId());

    }

    @Test
    public void getterAndSetterTest() {
        JokeDTO jokeDTO = new JokeDTO();
        jokeDTO.setId("id");
        jokeDTO.setText("test");
        assertNotNull(jokeDTO);
        assertNotNull(jokeDTO.getId());
        assertNotNull(jokeDTO.getText());
    }


    @Test
    public void builderTest() {
        JokeDTO jokeDTO = JokeDTO.builder()
                .id("id")
                .text("text")
                .build();
        assertNotNull(jokeDTO);
        assertNotNull(jokeDTO.getId());
        assertNotNull(jokeDTO.getText());
    }
}
