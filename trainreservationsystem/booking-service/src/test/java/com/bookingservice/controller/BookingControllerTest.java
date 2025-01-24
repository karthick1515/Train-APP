package com.bookingservice.controller;

import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bookingservice.controller.BookingController;
import com.bookingservice.dto.BookingResponseDTO;
import com.bookingservice.exceptions.BookingDetailsNotFoundException;
import com.bookingservice.model.BookingDetails;
import com.bookingservice.service.BookingService;


@ExtendWith(MockitoExtension.class)
public class BookingControllerTest {


	    @Mock
	    private BookingService bookingService;
	    
		
		 @InjectMocks
		    private BookingController bookingController;

	    @Test
	    public void testGetAllDetailsForUser() throws BookingDetailsNotFoundException {
	        // Arrange
	        String userName = "JohnDoe";
	        List<BookingDetails> bookingDetailsList = new ArrayList<>(); // Create sample booking details
	        when(bookingService.getAllDetailsForUser(userName)).thenReturn(bookingDetailsList);

	        // Act
	        ResponseEntity<List<BookingDetails>> response = bookingController.getAllDetailsForUser(userName);

	        // Assert
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(bookingDetailsList, response.getBody());
	    }

	    @Test
	    public void testGetUserDetailsByPnr() throws BookingDetailsNotFoundException {
	        // Arrange
	        long pnrNo = 1234567890L;
	        BookingDetails bookingDetails = new BookingDetails(); // Create sample booking details
	        when(bookingService.getUserDetailsByPnr(pnrNo)).thenReturn(bookingDetails);

	        // Act
	        ResponseEntity<BookingDetails> response = bookingController.getUserDetailsByPnr(pnrNo);

	        // Assert
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(bookingDetails, response.getBody());
	    }

	    @Test
	    public void testAddUserBookingDetails() {
	        // Arrange
	        BookingDetails userDetails = new BookingDetails(); // Create sample booking details
	        BookingResponseDTO responseDTO = new BookingResponseDTO(); // Create a sample response
	        when(bookingService.addUserBookingDetails(userDetails)).thenReturn(responseDTO);

	        // Act
	        ResponseEntity<BookingResponseDTO> response = bookingController.addUserBookingDetails(userDetails);

	        // Assert
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(responseDTO, response.getBody());
	    }

	    @Test
	    public void testCancelUserBookingDetails() throws BookingDetailsNotFoundException {
	        // Arrange
	        long pnrNo = 1234567890L;
	        String cancellationMessage = "Booking canceled successfully"; // Create a sample cancellation message
	        when(bookingService.cancelUserBookingDetails(pnrNo)).thenReturn(cancellationMessage);

	        // Act
	        ResponseEntity<String> response = bookingController.cancelUserBookingDetails(pnrNo);

	        // Assert
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(cancellationMessage, response.getBody());
	    }

}
