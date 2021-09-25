package com.tech.test.exception;

import com.tech.test.model.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
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

    public static final String ERROR_HANDLED = "Error handled {}";

    /**
     * Handles ResourceNotFoundException
     * retuns a ResponseEntity
     * with 404 code
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.info(ERROR_HANDLED, ex.getMessage());

        return new ResponseEntity<>((ErrorDTO.builder()
                .code(404)
                .message(ex.getMessage())
                .level("WARNING"))
                .build(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles HttpClientErrorException
     * retuns a ResponseEntity
     * with 404 code
     */
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorDTO> handleHttpClientErrorException(HttpClientErrorException ex) {
        log.info(ERROR_HANDLED, ex.getMessage());

        return new ResponseEntity<>((ErrorDTO.builder()
                .code(ex.getRawStatusCode())
                .message("Unable to complete request. Please try again later.")
                .level("ERROR"))
                .build(), ex.getStatusCode());
    }

    /**
     * Handles ResourceNotFoundException
     * retuns a ResponseEntity
     * with 503 code
     */
    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ErrorDTO> handleHttpServerErrorException(HttpServerErrorException ex) {
        log.info(ERROR_HANDLED, ex.getMessage());

        return new ResponseEntity<>((ErrorDTO.builder()
                .code(ex.getRawStatusCode())
                .message("Unable to complete request. " + ex.getMessage())
                .level("ERROR"))
                .build(), ex.getStatusCode());
    }



}
