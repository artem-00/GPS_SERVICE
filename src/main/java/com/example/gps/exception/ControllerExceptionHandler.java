package com.example.gps.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex, WebRequest request) {
        logger.error("error 400");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error 400(bad request)");
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<String> notSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        logger.error("error 405");
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("error 405(method not supported)");
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> internalServerErrorException(RuntimeException ex, WebRequest request) {
        logger.error("error 500");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error 500(internal server error)");
    }
}