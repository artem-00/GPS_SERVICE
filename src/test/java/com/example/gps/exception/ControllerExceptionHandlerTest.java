package com.example.gps.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.ServletWebRequest;

import static org.junit.jupiter.api.Assertions.*;

class ControllerExceptionHandlerTest {

    @Test
    void handleHttpClientErrorException() {
        HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Bad request");
        ControllerExceptionHandler handler = new ControllerExceptionHandler();

        ResponseEntity<String> response = handler.handleHttpClientErrorException(ex, new ServletWebRequest(new MockHttpServletRequest()));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("error 400(bad request)", response.getBody());
    }

    @Test
    void notSupportedException() {
        HttpRequestMethodNotSupportedException ex = new HttpRequestMethodNotSupportedException("GET");
        ControllerExceptionHandler handler = new ControllerExceptionHandler();

        ResponseEntity<String> response = handler.notSupportedException(ex, new ServletWebRequest(new MockHttpServletRequest()));

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        assertEquals("error 405(method not supported)", response.getBody());
    }

    @Test
    void internalServerErrorException() {
        RuntimeException ex = new RuntimeException("Internal Server Error");
        ControllerExceptionHandler handler = new ControllerExceptionHandler();

        ResponseEntity<String> response = handler.internalServerErrorException(ex, new ServletWebRequest(new MockHttpServletRequest()));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("error 500(internal server error)", response.getBody());
    }
}
