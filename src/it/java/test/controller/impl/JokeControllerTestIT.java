package test.controller.impl;

import com.tech.test.TestApplication;
import com.tech.test.controller.impl.JokeControllerImpl;
import com.tech.test.model.dto.JokeDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:resources/application-test.properties")
public class JokeControllerTestIT {

    @Autowired
    RestTemplate rest;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JokeControllerImpl controller;

    @LocalServerPort
    private int port;

    @Value("${host}")
    private String host;

    @Test
    public void getRandomJoke() throws Exception {
        var result = mvc.perform(post("/v1/joke-request"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasKey("id")))
                .andExpect(jsonPath("$", hasKey("text")));
    }

    @Test
    public void getJokeById() throws Exception {

        var id = rest.postForObject(createURLWithPort("/v1/joke-request"), null, JokeDTO.class).getId();
        var result = mvc.perform(get("/v1/joke/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasKey("id")))
                .andExpect(jsonPath("$", hasKey("text")));
    }


    private String createURLWithPort(String uri) {
        return host + port + uri;
    }

}
