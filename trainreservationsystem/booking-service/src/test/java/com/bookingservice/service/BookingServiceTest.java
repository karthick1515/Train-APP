package com.bookingservice.service;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import org.springframework.web.client.RestTemplate;

import com.bookingservice.client.TrainFeignClient;
import com.bookingservice.dto.BookingResponseDTO;
import com.bookingservice.dto.TrainDetails;
import com.bookingservice.enums.BookingClass;
import com.bookingservice.exceptions.BookingDetailsNotFoundException;
import com.bookingservice.model.BookingDetails;
import com.bookingservice.model.Passenger;
import com.bookingservice.repository.BookingRepository;
import com.bookingservice.repository.PassengerRepository;
import com.bookingservice.service.BookingServiceImpl;
//import com.bookingservice.service.SequenceGeneratorService;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

	@Mock
	private BookingRepository bookingRepository;

	@Mock
	private PassengerRepository passengerRepository;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private TrainFeignClient trainFeignClient;

//	@Mock
//	private SequenceGeneratorService sequenceGeneratorService;

	@InjectMocks
	private BookingServiceImpl bookingService;

	@Test
	public void testGetAll() {
		List<BookingDetails> expectedList = new ArrayList<>();
		expectedList.add(new BookingDetails());
		expectedList.add(new BookingDetails());

		Mockito.when(bookingRepository.findAll()).thenReturn(expectedList);
		List<BookingDetails> result = bookingService.getAll();
		Mockito.verify(bookingRepository, Mockito.times(1)).findAll(); // Verify that the findAll method was called
																		// exactly once
		Assertions.assertIterableEquals(expectedList, result); // Compare the expected result and the actual result
	}

	@Test
	public void testGetAllDetailsForUser() throws BookingDetailsNotFoundException {

		String userName = "testUser";
		BookingDetails booking1 = new BookingDetails();
		BookingDetails booking2 = new BookingDetails();
		List<BookingDetails> expectedList = new ArrayList<>();
		expectedList.add(booking1);
		expectedList.add(booking2);

		List<Passenger> passengers1 = new ArrayList<>();
		passengers1.add(new Passenger());
		passengers1.add(new Passenger());

		List<Passenger> passengers2 = new ArrayList<>();
		passengers2.add(new Passenger());

		booking1.setPnrNo(12345L);
		booking2.setPnrNo(67890L);

		Mockito.when(bookingRepository.findByUserName(userName)).thenReturn(Optional.of(expectedList));
		Mockito.when(passengerRepository.findByPnrNo(12345L)).thenReturn(passengers1);
		Mockito.when(passengerRepository.findByPnrNo(67890L)).thenReturn(passengers2);

		// Act
		List<BookingDetails> result = bookingService.getAllDetailsForUser(userName);

		// Assert
		Mockito.verify(bookingRepository).findByUserName(userName); // Verify that findByUserName was called
		Mockito.verify(passengerRepository, Mockito.times(2)).findByPnrNo(Mockito.anyLong()); // Verify that findByPnrNo
																								// was called twice
		Assertions.assertEquals(expectedList.size(), result.size());
	}

	@Test
	public void testGetAllDetailsForUserNoBookings() {
		// Arrange
		String userName = "userWithNoBookings";
		Mockito.when(bookingRepository.findByUserName(userName)).thenReturn(Optional.empty());

		// Act and Assert
		Assertions.assertThrows(BookingDetailsNotFoundException.class,
				() -> bookingService.getAllDetailsForUser(userName));
	}

	@Test
	public void testGetUserDetailsByPnr() throws BookingDetailsNotFoundException {
		// Arrange
		long pnrNo = 12345L;
		BookingDetails expectedBooking = new BookingDetails();
		expectedBooking.setPnrNo(pnrNo);
		// Assuming you have some sample Passenger entities
		Passenger passenger1 = new Passenger();
		passenger1.setPnrNo(pnrNo);
		Passenger passenger2 = new Passenger();
		passenger2.setPnrNo(pnrNo);
		expectedBooking.setPassengers(List.of(passenger1, passenger2));

		Mockito.when(bookingRepository.findByPnrNo(pnrNo)).thenReturn(Optional.of(expectedBooking));
		Mockito.when(passengerRepository.findByPnrNo(pnrNo)).thenReturn(List.of(passenger1, passenger2));

		// Act
		BookingDetails result = bookingService.getUserDetailsByPnr(pnrNo);

		// Assert
		Mockito.verify(bookingRepository).findByPnrNo(pnrNo); // Verify that findByPnrNo was called
		Mockito.verify(passengerRepository).findByPnrNo(pnrNo); // Verify that findByPnrNo was called
		Assertions.assertEquals(expectedBooking, result);
	}

	@Test
	public void testGetUserDetailsByPnrNotFound() {
		// Arrange
		long pnrNo = 99999L; // Assuming a non-existent PNR
		Mockito.when(bookingRepository.findByPnrNo(pnrNo)).thenReturn(Optional.empty());

		// Act and Assert
		Assertions.assertThrows(BookingDetailsNotFoundException.class, () -> bookingService.getUserDetailsByPnr(pnrNo));
	}

	@Test
	public void testAddUserBookingDetails() {
	    // Arrange
	    BookingDetails userDetails = BookingDetails.builder()
	        .bookingId("1")
	        .userName("John Doe")
	        .trainNo(12345)
	        .classType(BookingClass.THIRDAC)
	        .passengerNo(3).journeyDate(new Date())
	        .passengers(createSamplePassengers())
	        .build();

	    TrainDetails trainDetails = Mockito.mock(TrainDetails.class);
	    Map<BookingClass, Integer> seatAvailability = new HashMap<>();
	    seatAvailability.put(BookingClass.THIRDAC, 30);
	    Mockito.when(trainDetails.getSeatAvailability()).thenReturn(seatAvailability);
	    Map<BookingClass, Integer> fares = new HashMap<>();
	    fares.put(BookingClass.THIRDAC, 50); // Set your desired fare for SLEEPER class
	    Mockito.when(trainDetails.getFares()).thenReturn(fares);

//	    when(sequenceGeneratorService.getSequenceNumber("booking_sequence")).thenReturn(1);
	    when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(TrainDetails.class))).thenReturn(trainDetails);
	    when(bookingRepository.save(Mockito.any(BookingDetails.class))).thenReturn(userDetails);
	    when(passengerRepository.saveAll(Mockito.anyList())).thenReturn(userDetails.getPassengers());
	    BookingClass expectedClassType = BookingClass.THIRDAC;
	    when(trainFeignClient.updateSeatsForBooking(userDetails.getTrainNo(), userDetails.getPassengerNo(), userDetails.getClassType())).thenReturn(trainDetails);

	    // Act
	    BookingResponseDTO result = bookingService.addUserBookingDetails(userDetails);

	    // Assert
	    Assertions.assertNotNull(result);
//	    Mockito.verify(sequenceGeneratorService, Mockito.times(1)).getSequenceNumber("booking_sequence");
	    Mockito.verify(restTemplate, Mockito.times(1)).getForObject(Mockito.anyString(), Mockito.eq(TrainDetails.class));
	    Mockito.verify(bookingRepository, Mockito.times(1)).save(userDetails);
	    Mockito.verify(passengerRepository, Mockito.times(1)).saveAll(userDetails.getPassengers());
	    Mockito.verify(trainFeignClient, Mockito.times(1)).updateSeatsForBooking(userDetails.getTrainNo(), userDetails.getPassengerNo(), expectedClassType);
	    Mockito.verify(trainDetails, Mockito.times(1)).getSeatAvailability();
	    Mockito.verify(trainDetails, Mockito.times(1)).getFares();
	}

	public static List<Passenger> createSamplePassengers() {
		return List.of(Passenger.builder().passengerId("1").name("Passenger 1").age(30).build(),
				Passenger.builder().passengerId("2").name("Passenger 2").age(25).build(),
				Passenger.builder().passengerId("3").name("Passenger 3").age(40).build());
	}
	
	@Test
	public void testCancelUserBookingDetails_SuccessfulCancellation() throws BookingDetailsNotFoundException {
	    // Arrange
	    long pnrNo = 1234567890L;
	    BookingDetails bookingDetails = new BookingDetails(); // Create a BookingDetails object as needed
	    when(bookingRepository.findByPnrNo(pnrNo)).thenReturn(Optional.of(bookingDetails));

	    // Act
	    String result = bookingService.cancelUserBookingDetails(pnrNo);

	    // Assert
//	    Assertions.assertEquals("Your booking ticket with PNR Number:" + pnrNo + "is cancelled. Your payment amount will be credited to your account within 5 to 7 days..!!!", result);
	    verify(bookingRepository, times(1)).save(bookingDetails);
	    verify(trainFeignClient, times(1)).updateSeatsForCancel(bookingDetails.getTrainNo(), bookingDetails.getPassengerNo(), bookingDetails.getClassType());
	}

    @Test
    public void testCancelUserBookingDetails_BookingDetailsNotFoundException() {
        // Arrange
        long pnrNo = 1234567890L;
        when(bookingRepository.findByPnrNo(pnrNo)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(BookingDetailsNotFoundException.class, () -> bookingService.cancelUserBookingDetails(pnrNo));
        verify(bookingRepository, never()).save(Mockito.any());
        verify(trainFeignClient, never()).updateSeatsForCancel(Mockito.anyInt(), Mockito.anyInt(), Mockito.any());
    }

}
