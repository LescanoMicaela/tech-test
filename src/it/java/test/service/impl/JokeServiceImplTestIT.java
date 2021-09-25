package test.service.impl;


import com.tech.test.TestApplication;
import com.tech.test.service.IJokeService;
import com.tech.test.service.impl.JokeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class JokeServiceImplTestIT {

    @Autowired
    private IJokeService service;


    @Test
    public void getRandomJokeOk() {
        var response = service.getRandomJoke();
        assertNotNull(response);
        assertNotNull(response.getText());
        assertNotNull(response.getId());

    }

    @TestConfiguration
    static class JokeServiceImplTestContextConfiguration {
        @Autowired
        RestTemplate rest;

        @Value("${chuck-api.endpoint.random}")
        String randomJokeEndpoint;

        @Bean
        public IJokeService jokeService() {
            return new JokeServiceImpl(randomJokeEndpoint, rest);
        }

    }


}
