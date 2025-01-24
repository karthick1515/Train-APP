package com.trainservice.controller;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.trainservice.controller.TrainController;
import com.trainservice.enums.BookingClass;
import com.trainservice.exceptions.TrainAlreadyPresent;
import com.trainservice.exceptions.TrainDetailsNotFoundException;
import com.trainservice.model.TrainDetails;
import com.trainservice.service.TrainService;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {


    @Mock
    private TrainService trainService;
	
	@InjectMocks
	private TrainController trainController;

    @Test
    public void testAddTrain() throws TrainAlreadyPresent {
        TrainDetails trainDetails = new TrainDetails();
        when(trainService.addTrain(trainDetails)).thenReturn(trainDetails);

        ResponseEntity<TrainDetails> response = trainController.addTrain(trainDetails);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(trainDetails, response.getBody());
    }

    @Test
    public void testTrainBetweenStationsandDate() {
        String sourceStation = "SourceStation";
        String destinationStation = "DestinationStation";
        String departureDate = "2023-01-01";

        List<TrainDetails> trainDetailsList = new ArrayList<>();
        when(trainService.trainBetweenStationsandDate(Mockito.eq(sourceStation), Mockito.eq(destinationStation), Mockito.anyString())).thenReturn(trainDetailsList);

        ResponseEntity<List<TrainDetails>> response = trainController.trainBetweenStationsandDate(sourceStation, destinationStation, departureDate);

       Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
       Assertions.assertEquals(trainDetailsList, response.getBody());
    }

    @Test
    public void testUpdateTrainDetailsByTrainNumber() throws TrainDetailsNotFoundException {
        int trainNumber = 123;
        TrainDetails trainDetails = new TrainDetails();
        when(trainService.updateTrainDetailsByTrainNumber(trainNumber, trainDetails)).thenReturn(trainDetails);

        ResponseEntity<TrainDetails> response = trainController.updateTrainDetailsByTrainNumber(trainNumber, trainDetails);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(trainDetails, response.getBody());
    }

    @Test
    public void testFindTrainByTrainNumber() throws TrainDetailsNotFoundException {
        int trainNumber = 123;
        TrainDetails trainDetails = new TrainDetails();
        when(trainService.findTrainByTrainNumber(trainNumber)).thenReturn(trainDetails);

        ResponseEntity<TrainDetails> response = trainController.findTrainByTrainNumber(trainNumber);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(trainDetails, response.getBody());
    }

    @Test
    public void testDeleteTrainByTrainNumber() throws TrainDetailsNotFoundException {
        int trainNumber = 123;
        when(trainService.deleteTrainByTrainNumber(trainNumber)).thenReturn("Train deleted successfully");

        ResponseEntity<String> response = trainController.deleteTrainByTrainNumber(trainNumber);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Train deleted successfully", response.getBody());
    }

    @Test
    public void testUpdateSeatsForBooking() throws TrainDetailsNotFoundException {
        int trainNumber = 123;
        int noOfPassengers = 2;
        BookingClass classType = BookingClass.SLEEPER;
        TrainDetails updatedTrainDetails = new TrainDetails(); // Replace with the expected updated train details

        when(trainService.updateSeatsforBooking(trainNumber, noOfPassengers, classType)).thenReturn(updatedTrainDetails);

        ResponseEntity<TrainDetails> response = trainController.updateSeatsForBooking(trainNumber, noOfPassengers, classType);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        // Add assertions for the expected updated train details
    }

    @Test
    public void testUpdateSeatsForCancel() throws TrainDetailsNotFoundException {
        int trainNumber = 123;
        int noOfPassengers = 2;
        BookingClass classType = BookingClass.THIRDAC;
        TrainDetails updatedTrainDetails = new TrainDetails(); // Replace with the expected updated train details

        when(trainService.updateSeatsforCancel(trainNumber, noOfPassengers, classType)).thenReturn(updatedTrainDetails);

        ResponseEntity<TrainDetails> response = trainController.updateSeatsForCancel(trainNumber, noOfPassengers, classType);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        // Add assertions for the expected updated train details
    }

//    @Test
//    public void testListOFDestinationStations() {
//    	 List<String> destinationStations = List.of("Chennai","Mumbai","Goa","Kerala");
//        when(trainService.lisOfDestinationStations()).thenReturn(destinationStations);
//
//        ResponseEntity<List<String>> response = trainController.listOFDestinationStations();
//
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertEquals(destinationStations, response.getBody());
//    }
//
//    @Test
//    public void testListOFSourceStations() {
//        List<String> sourceStations = List.of("Chennai","Mumbai","Goa","Kerala");
//        when(trainService.lisOfSourceStations()).thenReturn(sourceStations);
//
//        ResponseEntity<List<String>> response = trainController.listOFSourceStations();
//
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertEquals(sourceStations, response.getBody());
//    }

    @Test
    public void testGetalltrain() {
    	 List<TrainDetails> trainDetailsList = new ArrayList<>();
        when(trainService.getalltheTrains()).thenReturn(trainDetailsList);

        ResponseEntity<List<TrainDetails>> response = trainController.getalltrain();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(trainDetailsList, response.getBody());
    }
}

