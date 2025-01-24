package com.trainservice.exceptions;
import static org.mockito.Mockito.mock;

import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
public class CustomizedRespTest {
	@InjectMocks
    private CustomizedResponseEntityExceptionHandler exceptionHandler;

    @Mock
    private Logger log;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void handleAllExceptions() {
        Exception ex = new Exception("Test Exception");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<Object> response = exceptionHandler.handleAllExceptions(ex, request);

        ExceptionResponse exceptionResponse = (ExceptionResponse) response.getBody();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Test Exception", exceptionResponse.getMessage());
        // Add more assertions as needed
    }

    @Test
    public void handleNotFoundException() {
        TrainDetailsNotFoundException ex = new TrainDetailsNotFoundException("Train not found");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<ExceptionResponse> response = exceptionHandler.handleNotFoundException(ex, request);

        ExceptionResponse exceptionResponse = response.getBody();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Train not found", exceptionResponse.getMessage());
        // Add more assertions as needed
    }

    @Test
    public void handleConflictException() {
        TrainAlreadyPresent ex = new TrainAlreadyPresent("Train already exists");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<ExceptionResponse> response = exceptionHandler.handleConflictException(ex, request);

        ExceptionResponse exceptionResponse = response.getBody();

        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
        assertEquals("Train already exists", exceptionResponse.getMessage());
        // Add more assertions as needed
    }

    

}
