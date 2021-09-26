package com.tech.test.controller.impl;

import com.tech.test.controller.IJokeController;
import com.tech.test.exception.ControllerAdvisor;
import com.tech.test.exception.ResourceNotFoundException;
import com.tech.test.model.dto.ErrorDTO;
import com.tech.test.model.dto.JokeDTO;
import com.tech.test.service.IJokeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class JokeControllerTest {


    private IJokeController controller;
    private JokeDTO joke;
    private ErrorDTO errorDTO;
    @Mock
    private IJokeService service;
    @Mock
    private RestTemplate rest;
    private MockMvc mvc;

    @Before
    public void setUp() {
        controller = new JokeControllerImpl(service);
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerAdvisor())
                .build();

        joke = JokeDTO.builder()
                .id("id")
                .text("text")
                .build();
        errorDTO = ErrorDTO.builder()
                .code(404)
                .message("message")
                .build();

    }

    @Test
    public void getRandomJokeOk() {
        var expected = ResponseEntity.ok(joke);
        when(service.getRandomJoke()).thenReturn(joke);

        var actual = controller.getRandomJoke();

        assertNotNull(actual);
        assertEquals(expected, actual);
        verify(service, times(1)).getRandomJoke();
    }

    @Test
    public void getRandomJokeWillReturn404Code() throws Exception {

        when(service.getRandomJoke()).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        mvc.perform(post("/v1/joke-request"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getRandomJokeWillReturn503Code() throws Exception {
        when(service.getRandomJoke()).thenThrow(new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE));
        mvc.perform(post("/v1/joke-request"))
                .andExpect(status().is5xxServerError());
    }


    @Test
    public void getJokeByIdOk() {
        var expected = ResponseEntity.ok(joke);
        when(service.getJokeById("id")).thenReturn(joke);

        var actual = controller.getJoke("id");

        assertNotNull(actual);
        assertEquals(expected, actual);
        verify(service, times(1)).getJokeById("id");
    }

    @Test
    public void getJokeByIdWillReturn404Code() throws Exception {

        when(service.getJokeById("id")).thenThrow(new ResourceNotFoundException("Joke not found"));
        mvc.perform(get("/v1/joke/id"))
                .andExpect(status().isNotFound());
    }

}
