package com.trainservice.exceptions;



import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ExceptionTest {
	
    @Test
    public void testParameterizedConstructor() {
        LocalDate timestamp = LocalDate.now();
        String message = "Test exception message";
        String details = "Test exception details";
        String httpCodeMessage = "HTTP 500 Internal Server Error";

        ExceptionResponse exceptionResponse = new ExceptionResponse(timestamp, message, details, httpCodeMessage);

        assertEquals(timestamp, exceptionResponse.getTimestamp());
        assertEquals(message, exceptionResponse.getMessage());
        assertEquals(details, exceptionResponse.getDetails());
        assertEquals(httpCodeMessage, exceptionResponse.getHttpCodeMessage());
    }

    @Test
    public void testDefaultConstructor() {
        ExceptionResponse exceptionResponse = new ExceptionResponse();

        // Ensure that the properties are initially null or default values
        assertEquals(null, exceptionResponse.getTimestamp());
        assertEquals(null, exceptionResponse.getMessage());
        assertEquals(null, exceptionResponse.getDetails());
        assertEquals(null, exceptionResponse.getHttpCodeMessage());
    }

    @Test
    public void testGetterAndSetterMethods() {
        ExceptionResponse exceptionResponse = new ExceptionResponse();

        LocalDate timestamp = LocalDate.now();
        String message = "Test exception message";
        String details = "Test exception details";
        String httpCodeMessage = "HTTP 500 Internal Server Error";

        exceptionResponse.setTimestamp(timestamp);
        exceptionResponse.setMessage(message);
        exceptionResponse.setDetails(details);
        exceptionResponse.setHttpCodeMessage(httpCodeMessage);

        assertEquals(timestamp, exceptionResponse.getTimestamp());
        assertEquals(message, exceptionResponse.getMessage());
        assertEquals(details, exceptionResponse.getDetails());
        assertEquals(httpCodeMessage, exceptionResponse.getHttpCodeMessage());
    }

}
