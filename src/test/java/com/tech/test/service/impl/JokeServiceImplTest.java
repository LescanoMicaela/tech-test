package com.tech.test.service.impl;


import com.tech.test.exception.ResourceNotFoundException;
import com.tech.test.model.dto.JokeDTO;
import com.tech.test.service.IJokeService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JokeServiceImplTest {

    private static final String ENDPOINT = "http://localhost:8080";
    private JokeDTO joke;

    private IJokeService service;

    @Mock
    private RestTemplate rest;


    @Before
    public void setUp() {
        service = new JokeServiceImpl(ENDPOINT, rest);
        joke = JokeDTO.builder()
                .id("id")
                .text("text")
                .build();
    }

    @Test
    public void getRandomJokeOk() {
        when(rest.getForObject(ENDPOINT, JokeDTO.class)).thenReturn(joke);
        var actual = service.getRandomJoke();
        assertNotNull(actual);
        assertEquals(joke.getId(), actual.getId());
        assertEquals(joke.getText(), actual.getText());

        verify(rest, times(1)).getForObject(ENDPOINT, JokeDTO.class);

    }

    @Test(expected = HttpServerErrorException.class)
    public void getRandomJokeWillThrowHttpServerExceptionTest() {
        when(rest.getForObject(ENDPOINT, JokeDTO.class)).thenThrow(new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE));
        service.getRandomJoke();
    }

    @Test(expected = HttpClientErrorException.class)
    public void getRandomJokeWillThrowHttpClientErrorExceptionTest() {
        when(rest.getForObject(ENDPOINT, JokeDTO.class)).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        service.getRandomJoke();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getRandomJokeWillThrowResourceNotFoundExceptionTest() {
        when(rest.getForObject(ENDPOINT, JokeDTO.class)).thenThrow(new ResourceNotFoundException("Not found"));
        service.getRandomJoke();
    }
}
