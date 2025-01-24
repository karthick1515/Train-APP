package com.trainservice.service;

import java.util.Date;
import java.util.List;

import com.trainservice.enums.BookingClass;
import com.trainservice.exceptions.TrainAlreadyPresent;
import com.trainservice.exceptions.TrainDetailsNotFoundException;
import com.trainservice.model.TrainDetails;

public interface TrainService {
    
   TrainDetails addTrain(TrainDetails trainDetails) throws TrainAlreadyPresent;
   
   List<TrainDetails> trainBetweenStationsandDate(String sourceStation,String destinationStation,String trainDate );

   TrainDetails updateTrainDetailsByTrainNumber(int trainNumber,TrainDetails trainDetails) throws TrainDetailsNotFoundException;

   TrainDetails findTrainByTrainNumber(int trainNumber)throws TrainDetailsNotFoundException;
   
   String deleteTrainByTrainNumber(int trainNumber) throws TrainDetailsNotFoundException;

   TrainDetails updateSeatsforBooking(int trainNumber, int noOfPassengers, BookingClass trainClassType ) throws TrainDetailsNotFoundException;
   TrainDetails updateSeatsforCancel(int trainNumber, int noOfPassengers, BookingClass trainClassType ) throws TrainDetailsNotFoundException;
   
   List<TrainDetails> getalltheTrains();
   List<String> lisOfDestinationStations();
   
 
   List<String> lisOfSourceStations();
}
