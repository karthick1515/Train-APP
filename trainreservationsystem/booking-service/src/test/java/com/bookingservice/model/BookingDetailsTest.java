package com.bookingservice.model;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.bookingservice.enums.BookingClass;

public class BookingDetailsTest {
	 @Test
	 public void testGettersAndSetters() {
	        BookingDetails bookingDetails = new BookingDetails();

	        // Set values using setters
//	        bookingDetails.setBookingId(1);
	        bookingDetails.setUserName("John Doe");
	        bookingDetails.setPnrNo(123456L);
	        bookingDetails.setTrainNo(789);
	        bookingDetails.setTrainName("Sample Train");
	        bookingDetails.setSourceStation("Source Station");
	        bookingDetails.setPassengers(new ArrayList<>());
	        bookingDetails.setDestinationStation("Destination Station");
	    //    bookingDetails.setJourneyDate(new Date());
	        bookingDetails.setTotalFare(100);
	        bookingDetails.setPassengerPhoneNumber(1234567890L);
	        bookingDetails.setClassType(BookingClass.SLEEPER);
	        bookingDetails.setBookingStatus("Confirmed");
	        bookingDetails.setPassengerNo(2);

	        // Ensure that the getters return the values set using setters
//	        assertEquals(1, bookingDetails.getBookingId());
	        assertEquals("John Doe", bookingDetails.getUserName());
	        assertEquals(123456L, bookingDetails.getPnrNo().longValue());
	        assertEquals(789, bookingDetails.getTrainNo());
	        assertEquals("Sample Train", bookingDetails.getTrainName());
	        assertEquals("Source Station", bookingDetails.getSourceStation());
	        assertEquals(new ArrayList<>(), bookingDetails.getPassengers());
	        assertEquals("Destination Station", bookingDetails.getDestinationStation());
	    //    assertEquals(new Date(), bookingDetails.getJourneyDate());
	        assertEquals(100, bookingDetails.getTotalFare());
	        assertEquals(1234567890L, bookingDetails.getPassengerPhoneNumber().longValue());
	        assertEquals(BookingClass.SLEEPER, bookingDetails.getClassType());
	        assertEquals("Confirmed", bookingDetails.getBookingStatus());
	        assertEquals(2, bookingDetails.getPassengerNo());
	    }
	 
	 
	 @Test
	    public void testDefaultConstructor() {
	        BookingDetails bookingDetails = new BookingDetails();

	        // Ensure that all fields are initialized with default values
//	        assertEquals(0, bookingDetails.getBookingId());
	        assertNull(bookingDetails.getUserName());
	        assertNull(bookingDetails.getPnrNo());
	        assertEquals(0, bookingDetails.getTrainNo());
	        assertNull(bookingDetails.getTrainName());
	        assertNull(bookingDetails.getSourceStation());
	        assertNull(bookingDetails.getPassengers());
	        assertNull(bookingDetails.getDestinationStation());
	        assertNull(bookingDetails.getJourneyDate());
	        assertEquals(0, bookingDetails.getTotalFare());
	        assertNull(bookingDetails.getPassengerPhoneNumber());
	        assertNull(bookingDetails.getClassType());
	        assertNull(bookingDetails.getBookingStatus());
	        assertEquals(0, bookingDetails.getPassengerNo());
	    }

	    @Test
	    public void testParameterizedConstructor() {
	        BookingDetails bookingDetails = new BookingDetails("1", "John Doe", 123456L, 789, "Sample Train",
	                "Source Station", null, "Destination Station", null, 100, 1234567890L,
	                BookingClass.SLEEPER, "Confirmed", 2);

	        // Ensure that the constructor initializes the fields correctly
//	        assertEquals(1, bookingDetails.getBookingId());
	        assertEquals("John Doe", bookingDetails.getUserName());
	        assertEquals(123456L, bookingDetails.getPnrNo().longValue());
	        assertEquals(789, bookingDetails.getTrainNo());
	        assertEquals("Sample Train", bookingDetails.getTrainName());
	        assertEquals("Source Station", bookingDetails.getSourceStation());
	        assertNull(bookingDetails.getPassengers());
	        assertEquals("Destination Station", bookingDetails.getDestinationStation());
	        assertNull(bookingDetails.getJourneyDate());
	        assertEquals(100, bookingDetails.getTotalFare());
	        assertEquals(1234567890L, bookingDetails.getPassengerPhoneNumber().longValue());
	        assertEquals(BookingClass.SLEEPER, bookingDetails.getClassType());
	        assertEquals("Confirmed", bookingDetails.getBookingStatus());
	        assertEquals(2, bookingDetails.getPassengerNo());
	    }
	
}
