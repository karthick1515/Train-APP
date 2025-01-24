package com.bookingservice.dto;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.bookingservice.enums.BookingClass;
import com.bookingservice.model.BookingDetails;
import com.bookingservice.model.Passenger;

public class BookingResponseDTOTest{
	
	@Test
    public void testDefaultConstructor() {
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();

        // Ensure all fields are initialized with default values (e.g., null or 0)
//        assertEquals(0, bookingResponseDTO.getBookingI());
        assertEquals(0, bookingResponseDTO.getPnrNo());
        assertEquals(0, bookingResponseDTO.getTrainNo());
        assertEquals(null, bookingResponseDTO.getTrainName());
        assertEquals(null, bookingResponseDTO.getSourceStation());
        assertEquals(null, bookingResponseDTO.getDestinationStation());
        assertEquals(null, bookingResponseDTO.getJourneyDate());
        assertEquals(0, bookingResponseDTO.getPassengerNo());
        assertEquals(null, bookingResponseDTO.getClassType());
        assertEquals(null, bookingResponseDTO.getBookingStatus());
        assertEquals(0, bookingResponseDTO.getTotalFare());
        assertEquals(null, bookingResponseDTO.getPass());
    }

    @Test
    public void testParameterizedConstructor() {
        // Create sample BookingDetails and List<Passenger>
        BookingDetails bookingDetails = new BookingDetails();
//        bookingDetails.setBookingId(1);
        bookingDetails.setPnrNo((long) 123456);
        bookingDetails.setTrainNo(789);
        bookingDetails.setTrainName("Sample Train");
        bookingDetails.setSourceStation("Source Station");
        bookingDetails.setDestinationStation("Destination Station");
        bookingDetails.setJourneyDate(new Date());
        bookingDetails.setPassengerNo(2);
        bookingDetails.setClassType(BookingClass.SLEEPER);
        bookingDetails.setBookingStatus("Confirmed");
        bookingDetails.setTotalFare(100);

        List<Passenger> passengers = List.of(new Passenger(), new Passenger());

        // Create a BookingResponseDTO using the constructor
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO(bookingDetails, passengers);

        // Ensure that the BookingResponseDTO fields are set correctly
//        assertEquals(1, bookingResponseDTO.getBookingId());
        assertEquals(123456, bookingResponseDTO.getPnrNo());
        assertEquals(789, bookingResponseDTO.getTrainNo());
        assertEquals("Sample Train", bookingResponseDTO.getTrainName());
        assertEquals("Source Station", bookingResponseDTO.getSourceStation());
        assertEquals("Destination Station", bookingResponseDTO.getDestinationStation());
        assertEquals(2, bookingResponseDTO.getPassengerNo());
        assertEquals(BookingClass.SLEEPER, bookingResponseDTO.getClassType());
        assertEquals("Confirmed", bookingResponseDTO.getBookingStatus());
        assertEquals(100, bookingResponseDTO.getTotalFare());
        assertEquals(passengers, bookingResponseDTO.getPass());
    }

    @Test
    public void testGettersAndSetters() {
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();

        // Set values using setters
//        bookingResponseDTO.setBookingId(1);
        bookingResponseDTO.setPnrNo(123456);
        bookingResponseDTO.setTrainNo(789);
        bookingResponseDTO.setTrainName("Sample Train");
        bookingResponseDTO.setSourceStation("Source Station");
        bookingResponseDTO.setDestinationStation("Destination Station");
        bookingResponseDTO.setJourneyDate(new Date());
        bookingResponseDTO.setPassengerNo(2);
        bookingResponseDTO.setClassType(BookingClass.SLEEPER);
        bookingResponseDTO.setBookingStatus("Confirmed");
        bookingResponseDTO.setTotalFare(100);

        // Ensure that the getters return the values set using setters
//        assertEquals(1, bookingResponseDTO.getBookingId());
        assertEquals(123456, bookingResponseDTO.getPnrNo());
        assertEquals(789, bookingResponseDTO.getTrainNo());
        assertEquals("Sample Train", bookingResponseDTO.getTrainName());
        assertEquals("Source Station", bookingResponseDTO.getSourceStation());
        assertEquals("Destination Station", bookingResponseDTO.getDestinationStation());
        assertEquals(2, bookingResponseDTO.getPassengerNo());
        assertEquals(BookingClass.SLEEPER, bookingResponseDTO.getClassType());
        assertEquals("Confirmed", bookingResponseDTO.getBookingStatus());
        assertEquals(100, bookingResponseDTO.getTotalFare());
    }

}
