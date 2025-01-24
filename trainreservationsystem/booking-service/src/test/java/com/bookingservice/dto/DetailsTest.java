package com.bookingservice.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.bookingservice.enums.BookingClass;
public class DetailsTest {
	 @Test
	    public void testGettersAndSetters() {
	        TrainDetails trainDetails = new TrainDetails();

	        // Set values using setters
	        trainDetails.setTrainNumber(12345);
	        trainDetails.setTrainName("Sample Train");
	        trainDetails.setSourceStation("Source Station");
	        trainDetails.setDestinationStation("Destination Station");
	        trainDetails.setDepartureTime("10:00 AM");
	        trainDetails.setArrivalTime("3:00 PM");
	        trainDetails.setTrainDate(new Date());

	        // Set seat availability using a map
	        Map<BookingClass, Integer> seatAvailability = new HashMap<>();
	        seatAvailability.put(BookingClass.SLEEPER, 10);
	        seatAvailability.put(BookingClass.SECONDAC, 20);
	        trainDetails.setSeatAvailability(seatAvailability);

	        // Set fares using a map
	        Map<BookingClass, Integer> fares = new HashMap<>();
	        fares.put(BookingClass.SLEEPER, 500);
	        fares.put(BookingClass.SECONDAC, 300);
	        trainDetails.setFares(fares);

	        // Ensure that the getters return the values set using setters
	        assertEquals(12345, trainDetails.getTrainNumber());
	        assertEquals("Sample Train", trainDetails.getTrainName());
	        assertEquals("Source Station", trainDetails.getSourceStation());
	        assertEquals("Destination Station", trainDetails.getDestinationStation());
	       // assertEquals("10:00 AM", trainDetails.getDepartureTime());
	       // assertEquals("3:00 PM", trainDetails.getArrivalTime());
	       // assertEquals(new Date(), trainDetails.getTrainDate());
	        assertEquals(10, trainDetails.getSeatAvailability().get(BookingClass.SLEEPER).intValue());
	        assertEquals(20, trainDetails.getSeatAvailability().get(BookingClass.SECONDAC).intValue());
	        assertEquals(500, trainDetails.getFares().get(BookingClass.SLEEPER).intValue());
	        assertEquals(300, trainDetails.getFares().get(BookingClass.SECONDAC).intValue());

	        // Test a getter for an unset property, which should return null
	       // assertNull(trainDetails.getDate());
	    }
}
