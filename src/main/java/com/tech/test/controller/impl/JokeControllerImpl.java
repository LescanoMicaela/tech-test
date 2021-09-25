package com.tech.test.controller.impl;

import com.tech.test.controller.IJokeController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1")
@Tag(name= "Joke Controller", description="Gets random jokes")
public class JokeControllerImpl implements IJokeController {
}
