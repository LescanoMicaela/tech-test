package com.tech.test.exception;

import com.tech.test.model.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class to advice the controllers
 * for ExceptionHandler
 * will return the ResponseEntity
 * for each exception
 */
@Slf4j
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    /**
     * Handles ResourceNotFoundException
     * retuns a ResponseEntity
     * with 404 code
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.info("Error handled {}", ex.getMessage());

        return new ResponseEntity<>((ErrorDTO.builder()
                .code(404)
                .message(ex.getMessage())
                .level("WARNING"))
                .build(), HttpStatus.NOT_FOUND);
    }
}
