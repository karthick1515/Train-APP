package com.bookingservice.exception;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.bookingservice.exceptions.ExceptionResponse;
public class ExceptionRespTest {

    @Test
    public void testParameterizedConstructor() {
        LocalDate timestamp = LocalDate.now();
        String message = "Test message";
        String details = "Test details";
        String httpCodeMessage = "HTTP 500 Internal Server Error";

        ExceptionResponse exceptionResponse = new ExceptionResponse(timestamp, message, details, httpCodeMessage);

        assertThat(exceptionResponse.getTimestamp()).isEqualTo(timestamp);
        assertThat(exceptionResponse.getMessage()).isEqualTo(message);
        assertThat(exceptionResponse.getDetails()).isEqualTo(details);
        assertThat(exceptionResponse.getHttpCodeMessage()).isEqualTo(httpCodeMessage);
    }

    @Test
    public void testDefaultConstructor() {
        ExceptionResponse exceptionResponse = new ExceptionResponse();

        assertThat(exceptionResponse.getTimestamp()).isNull();
        assertThat(exceptionResponse.getMessage()).isNull();
        assertThat(exceptionResponse.getDetails()).isNull();
        assertThat(exceptionResponse.getHttpCodeMessage()).isNull();
    }

    @Test
    public void testGetterAndSetterMethods() {
        ExceptionResponse exceptionResponse = new ExceptionResponse();

        LocalDate timestamp = LocalDate.now();
        String message = "Test message";
        String details = "Test details";
        String httpCodeMessage = "HTTP 500 Internal Server Error";

        exceptionResponse.setTimestamp(timestamp);
        exceptionResponse.setMessage(message);
        exceptionResponse.setDetails(details);
        exceptionResponse.setHttpCodeMessage(httpCodeMessage);

        assertThat(exceptionResponse.getTimestamp()).isEqualTo(timestamp);
        assertThat(exceptionResponse.getMessage()).isEqualTo(message);
        assertThat(exceptionResponse.getDetails()).isEqualTo(details);
        assertThat(exceptionResponse.getHttpCodeMessage()).isEqualTo(httpCodeMessage);
    }
}
