package com.bookingservice.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.bookingservice.exceptions.BookingDetailsNotFoundException;
import com.bookingservice.exceptions.CustomizedResponseEntityExceptionHandler;
import com.bookingservice.exceptions.ExceptionResponse;

@ExtendWith(MockitoExtension.class)
public class CustomisedResponseTest {

	  @Mock
	  private WebRequest webRequest;
	
	  @InjectMocks
	  private CustomizedResponseEntityExceptionHandler exceptionHandler;

	

	    @Test
	    public void testHandleAllExceptions() {
	        Exception exception = new Exception("Test Exception");
	        
	        ResponseEntity<Object> response = exceptionHandler.handleAllExceptions(exception, webRequest);

	        Assertions.assertEquals(500, response.getStatusCodeValue()); // Check if it returns Internal Server Error status
	    }

	    @Test
	    public void testHandleNotFoundException() {
	        BookingDetailsNotFoundException exception = new BookingDetailsNotFoundException("Test Not Found Exception");
	        
	        ResponseEntity<ExceptionResponse> response = exceptionHandler.handleNotFoundException(exception, webRequest);

	       Assertions.assertEquals(404, response.getStatusCodeValue()); // Check if it returns Not Found status
	    }

//	    @Test
//	    public void testHandleMethodArgumentNotValid() {
//	        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, null);
//	        when(exception.getBindingResult().getFieldErrors()).thenReturn(Collections.singletonList(new FieldError("objectName", "fieldName", "Field error message")));
//	        
//	        ResponseEntity<Object> response = exceptionHandler.handleMethodArgumentNotValid(exception, null, null, webRequest);
//
//	        assertEquals(400, response.getStatusCodeValue()); // Check if it returns Bad Request status
//	        // You can add more assertions to check the response details
//	    }

}
