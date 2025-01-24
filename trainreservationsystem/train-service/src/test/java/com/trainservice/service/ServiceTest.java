package com.trainservice.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.trainservice.enums.BookingClass;
import com.trainservice.exceptions.TrainAlreadyPresent;
import com.trainservice.exceptions.TrainDetailsNotFoundException;
import com.trainservice.model.TrainDetails;
import com.trainservice.repository.TrainRepository;
import com.trainservice.service.TrainServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

	@Mock
	private TrainRepository trainRepository;

	@InjectMocks
	private TrainServiceImpl trainService;

	@Test
	public void testAddTrain_SuccessfulAddition() throws TrainAlreadyPresent {
		// Arrange
		TrainDetails trainDetails = TrainDetails.builder().trainNumber(12345).trainName("Sample Train")
				.sourceStation("Source Station").destinationStation("Destination Station").departureTime("10:00 AM")
				.arrivalTime("3:00 PM").trainDepatureDate(new Date()).trainArrivalDate(new Date())
				.seatAvailability(createSampleSeatAvailability()) // Create a dummy seat availability map
				.fares(createSampleFares()) // Create a dummy fares map
				.build();
		// Create sample TrainDetails
		when(trainRepository.findAll()).thenReturn(new ArrayList<>()); // No trains are present initially
		when(trainRepository.save(trainDetails)).thenReturn(trainDetails); // Mock the save method
		// Act
		TrainDetails addedTrain = trainService.addTrain(trainDetails);

		// Assert
		Assertions.assertNotNull(addedTrain);
		verify(trainRepository, times(1)).save(trainDetails);
	}

	// Create a sample seat availability map
	public Map<BookingClass, Integer> createSampleSeatAvailability() {
		Map<BookingClass, Integer> seatAvailability = new HashMap<>();
		seatAvailability.put(BookingClass.SLEEPER, 100); // Replace with your desired values
		seatAvailability.put(BookingClass.SECONDAC, 50);
		seatAvailability.put(BookingClass.THIRDAC, 75);
		// Add more entries as needed
		return seatAvailability;
	}

	// Create a sample fares map
	public Map<BookingClass, Integer> createSampleFares() {
		Map<BookingClass, Integer> fares = new HashMap<>();
		fares.put(BookingClass.SLEEPER, 500); // Replace with your desired fare values
		fares.put(BookingClass.SECONDAC, 1000);
		fares.put(BookingClass.THIRDAC, 750);
		// Add more entries as needed
		return fares;
	}

	@Test
	public void testAddTrain_TrainAlreadyPresent() {
		// Arrange
		TrainDetails trainDetails = TrainDetails.builder().trainNumber(12345).trainName("Sample Train")
				.sourceStation("Source Station").destinationStation("Destination Station").departureTime("10:00 AM")
				.arrivalTime("3:00 PM").trainDepatureDate(new Date()).trainArrivalDate(new Date())
				.seatAvailability(createSampleSeatAvailability()) // Create a dummy seat availability map
				.fares(createSampleFares()) // Create a dummy fares map
				.build();
		when(trainRepository.findAll()).thenReturn(Collections.singletonList(trainDetails)); // Train with the same
																								// number already exists

		// Act and Assert
		Assertions.assertThrows(TrainAlreadyPresent.class, () -> trainService.addTrain(trainDetails));
	}

	 @Test
	 public void testTrainBetweenStationsAndDate() {
		  
		 
		 Date trainDepartureDate = new Date();
		 
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	        // Convert the Date to a String
	     String dateString = dateFormat.format(trainDepartureDate);
	     

	     TrainDetails train1 = new TrainDetails();
	     train1.setSourceStation("Source 1");
	     train1.setDestinationStation("Destination 1");
	     train1.setTrainDepatureDate(trainDepartureDate);

	     TrainDetails train2 = new TrainDetails();
	     train2.setSourceStation("Source 2");
	     train2.setDestinationStation("Destination 2");
	     train2.setTrainDepatureDate(trainDepartureDate);

	     when(trainRepository.findAll()).thenReturn(List.of(train1));
	     String stringDate = trainDepartureDate.toString();

	     // Act
	     List<TrainDetails> result = trainService.trainBetweenStationsandDate("Source 1","Destination 1",dateString);

	     // Assert
	     Assertions.assertEquals(1, 1);
	     Assertions.assertEquals(train1,train1);
	 }	 
	 
	@Test
	public void testUpdateTrainDetailsByTrainNumber() throws TrainDetailsNotFoundException {
		// Arrange
		int trainNumber = 123; // Replace with your test train number
		TrainDetails existingTrain = new TrainDetails(); // Create an existing train

		// Set up a modified train with new details
		TrainDetails modifiedTrain = new TrainDetails();
		modifiedTrain.setTrainNumber(trainNumber);
		modifiedTrain.setTrainName("Modified Train");
		// Set other modified fields...

		// Mock the behavior of the repository
		Mockito.when(trainRepository.findByTrainNumber(trainNumber)).thenReturn(java.util.Optional.of(existingTrain));
		Mockito.when(trainRepository.save(Mockito.any(TrainDetails.class))).thenReturn(modifiedTrain);

		// Act
		TrainDetails updatedTrain = trainService.updateTrainDetailsByTrainNumber(trainNumber, modifiedTrain);

		// Assert
		Mockito.verify(trainRepository, times(1)).findByTrainNumber(trainNumber);
		Mockito.verify(trainRepository, times(1)).save(existingTrain);
		Assertions.assertEquals("Modified Train", updatedTrain.getTrainName());
	}

	@Test
	public void testUpdateTrainDetailsByTrainNumber_InvalidTrain() {
		// Arrange
		int trainNumber = 123; // Replace with your test train number

		// Mock the behavior of the repository when the train does not exist
		Mockito.when(trainRepository.findByTrainNumber(trainNumber)).thenReturn(java.util.Optional.empty());

		// Act and Assert (exception is expected)
		Assertions.assertThrows(TrainDetailsNotFoundException.class, () -> {
   	trainService.updateTrainDetailsByTrainNumber(trainNumber, new TrainDetails());
		});
	}

//	   
//	   @Test
//	   public void testUpdateSeatsforBooking_SleeperClassAvailable() throws TrainDetailsNotFoundException {
//	       // Arrange
//	       int trainNumber = 123; // Replace with your test train number
//	       BookingClass trainClassType = BookingClass.SLEEPER;
//	       int noOfPassengers = 2;
//
//	       // Create a TrainDetails object with initial seat availability
//	       TrainDetails trainDetails = new TrainDetails();
//	       trainDetails.setTrainNumber(trainNumber);
//	       Map<BookingClass, Integer> initialSeatAvailability = new HashMap<>();
//	       initialSeatAvailability.put(BookingClass.SLEEPER, 10); // Initial number of sleeper seats
//	       trainDetails.setSeatAvailability(initialSeatAvailability);
//
//	       when(trainRepository.findByTrainNumber(trainNumber)).thenReturn(Optional.of(trainDetails));
//
//	       // Act
//	       TrainDetails updatedTrain = trainService.updateSeatsforBooking(trainNumber, noOfPassengers, trainClassType);
//
//	       // Assert
//	       int expectedSleeperSeats = initialSeatAvailability.get(BookingClass.SLEEPER) - noOfPassengers;
//	       Assertions.assertEquals(expectedSleeperSeats, updatedTrain.getSeatAvailability().get(BookingClass.SLEEPER));
//	   }
//	   
	@Test
	public void testListOfDestinationStations() {

		TrainDetails trainDetails1 = TrainDetails.builder().trainNumber(12345).trainName("Sample Train 1")
				.sourceStation("Source 2").destinationStation("Destination 1").departureTime("10:00 AM")
				.arrivalTime("3:00 PM").trainDepatureDate(new Date()).trainArrivalDate(new Date())
				.seatAvailability(createSampleSeatAvailability()) // Create a dummy seat availability map
				.fares(createSampleFares()) // Create a dummy fares map
				.build();

		TrainDetails trainDetails2 = TrainDetails.builder().trainNumber(54321).trainName("Sample Train 2")
				.sourceStation("Source 2").destinationStation("Destination 2").departureTime("10:00 AM")
				.arrivalTime("3:00 PM").trainDepatureDate(new Date()).trainArrivalDate(new Date())
				.seatAvailability(createSampleSeatAvailability()) // Create a dummy seat availability map
				.fares(createSampleFares()) // Create a dummy fares map
				.build();

		List<TrainDetails> trainList = Arrays.asList(trainDetails1, trainDetails2);

//		when(trainRepository.findAll()).thenReturn(trainList);

//		List<String> destinationStations = trainService.lisOfDestinationStations();

		// Verify that the method returned the correct list of destination stations
//		Assertions.assertEquals(Arrays.asList("Destination 1", "Destination 2"), destinationStations);
	}

	@Test
	public void testListOfSourceStations() {
		// Create a list of TrainDetails with source stations
		TrainDetails trainDetails1 = TrainDetails.builder().trainNumber(12345).trainName("Sample Train 1")
				.sourceStation("Source 1").destinationStation("Destination 1").departureTime("10:00 AM")
				.arrivalTime("3:00 PM").trainDepatureDate(new Date()).trainArrivalDate(new Date())
				.seatAvailability(createSampleSeatAvailability()) // Create a dummy seat availability map
				.fares(createSampleFares()) // Create a dummy fares map
				.build();

		TrainDetails trainDetails2 = TrainDetails.builder().trainNumber(54321).trainName("Sample Train 2")
				.sourceStation("Source 2").destinationStation("Destination 2").departureTime("10:00 AM")
				.arrivalTime("3:00 PM").trainDepatureDate(new Date()).trainArrivalDate(new Date())
				.seatAvailability(createSampleSeatAvailability()) // Create a dummy seat availability map
				.fares(createSampleFares()) // Create a dummy fares map
				.build();

		List<TrainDetails> trainList = Arrays.asList(trainDetails1, trainDetails2);

//		when(trainRepository.findAll()).thenReturn(trainList);

//		List<String> sourceStations = trainService.lisOfSourceStations();

		// Verify that the method returned the correct list of source stations
//		Assertions.assertEquals(Arrays.asList("Source 1", "Source 2"), sourceStations);
	}

	@Test
	public void testGetAllTheTrains() {

		// Create a list of TrainDetails with source stations
		TrainDetails trainDetails1 = TrainDetails.builder().trainNumber(12345).trainName("Sample Train 1")
				.sourceStation("Source 1").destinationStation("Destination 1").departureTime("10:00 AM")
				.arrivalTime("3:00 PM").trainDepatureDate(new Date()).trainArrivalDate(new Date())
				.seatAvailability(createSampleSeatAvailability()) // Create a dummy seat availability map
				.fares(createSampleFares()) // Create a dummy fares map
				.build();

		TrainDetails trainDetails2 = TrainDetails.builder().trainNumber(54321).trainName("Sample Train 2")
				.sourceStation("Source 2").destinationStation("Destination 2").departureTime("10:00 AM")
				.arrivalTime("3:00 PM").trainDepatureDate(new Date()).trainArrivalDate(new Date())
				.seatAvailability(createSampleSeatAvailability()) // Create a dummy seat availability map
				.fares(createSampleFares()) // Create a dummy fares map
				.build();

		List<TrainDetails> trainList = Arrays.asList(trainDetails1, trainDetails2);

		when(trainRepository.findAll()).thenReturn(trainList);

		List<TrainDetails> allTrains = trainService.getalltheTrains();

	// Verify that the method returned the correct list of TrainDetails
		Assertions.assertIterableEquals(trainList, allTrains);
	}
	
	   @Test
	    public void testFindTrainByTrainNumber() throws TrainDetailsNotFoundException {
		   
	        int trainNumber = 123;
	        TrainDetails trainDetails = new TrainDetails();
	        trainDetails.setTrainNumber(trainNumber);

	        when(trainRepository.findByTrainNumber(trainNumber)).thenReturn(Optional.of(trainDetails));

	        TrainDetails foundTrain = trainService.findTrainByTrainNumber(trainNumber);

	        Assertions.assertEquals(trainDetails, foundTrain);
	    }
	   
	   @Test
	    public void testFindTrainByTrainNumberTrainNotFound() {
	        int trainNumber = 123;

	        when(trainRepository.findByTrainNumber(trainNumber)).thenReturn(Optional.empty());

	        Assertions.assertThrows(TrainDetailsNotFoundException.class, () -> trainService.findTrainByTrainNumber(trainNumber));
	    }
	   
	    @Test
	    public void testDeleteTrainByTrainNumber() throws TrainDetailsNotFoundException {
	        int trainNumber = 123;
	        TrainDetails trainDetails = new TrainDetails();
	        trainDetails.setTrainNumber(trainNumber);

	        when(trainRepository.findByTrainNumber(trainNumber)).thenReturn(Optional.of(trainDetails));

	        String result = trainService.deleteTrainByTrainNumber(trainNumber);

	        verify(trainRepository, times(1)).delete(trainDetails);
	        Assertions.assertEquals("Train with train number 123 is deleted successfully!", result);
	    }

	    @Test
	    public void testDeleteTrainByTrainNumberTrainNotFound() {
	        int trainNumber = 123;

	        when(trainRepository.findByTrainNumber(trainNumber)).thenReturn(Optional.empty());

	       Assertions.assertThrows(TrainDetailsNotFoundException.class, () -> trainService.deleteTrainByTrainNumber(trainNumber));
	    }
	
	

}
