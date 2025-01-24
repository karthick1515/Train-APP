//package com.trainservice.service;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//import com.trainservice.enums.BookingClass;
//import com.trainservice.exceptions.TrainAlreadyPresent;
//import org.apache.logging.log4j.LogManager;
//
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.trainservice.exceptions.TrainDetailsNotFoundException;
//import com.trainservice.model.TrainDetails;
//import com.trainservice.repository.TrainRepository;
//
//@Service
//public class TrainServiceImpl implements TrainService {
//    public static final Logger log= LogManager.getLogger(TrainServiceImpl.class);
//
//    @Autowired
//    private TrainRepository trainRepository;
//
//    @Override
//    public TrainDetails addTrain(TrainDetails trainDetails) throws TrainAlreadyPresent {
//        log.info("Adding a new train with train number: {}", trainDetails.getTrainNumber());
//        List<TrainDetails> allTrainDetails = trainRepository.findAll();
//        for(TrainDetails train: allTrainDetails){
//            if(train.getTrainNumber()==trainDetails.getTrainNumber()){
//                log.error("Train with train number {} already present", trainDetails.getTrainNumber());
//                throw new TrainAlreadyPresent("Train with train number "+trainDetails.getTrainNumber()+" already present ");
//            }
//        }
//
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
////        //Date is entered from front end in the form of string
////        LocalDate localDepatureDate = LocalDate.parse(trainDetails.getDepatureDate(), formatter);
////        java.sql.Date sqlDate1 = java.sql.Date.valueOf(localDepatureDate);
////        java.util.Date utilDate1 = new java.util.Date(sqlDate1.getTime());
////        trainDetails.setTrainDepatureDate(utilDate1);
////
////        LocalDate localArrivalDate = LocalDate.parse(trainDetails.getArrivalDate(), formatter);
////        java.sql.Date sqlDate2 = java.sql.Date.valueOf(localArrivalDate);
////        java.util.Date utilDate2 = new java.util.Date(sqlDate2.getTime());
////        trainDetails.setTrainArrivalDate(utilDate2);
//
//
//        log.info("Saving the new train with train number: {}", trainDetails.getTrainNumber());
//        return trainRepository.save(trainDetails);
//    }
// @Override
//   public List<TrainDetails> trainBetweenStationsandDate(String sourceStation, String destinationStation,
//                                                         Date trainDepatureDate) {
//     log.info("Finding trains between stations {} and {} on date: {}", sourceStation, destinationStation, trainDepatureDate);
//
//        return trainRepository.findBySourceStationAndDestinationStationAndTrainDepatureDate(sourceStation, destinationStation, trainDepatureDate);
//
//    }
//
//    @Override
//    public TrainDetails updateTrainDetailsByTrainNumber(int trainNumber, TrainDetails trainDetails) throws TrainDetailsNotFoundException {
//        Optional<TrainDetails> train = trainRepository.findByTrainNumber(trainNumber);
//        if (train.isEmpty()) {
//            log.error("Train with train number {} not found for update", trainNumber);
//            throw new TrainDetailsNotFoundException("Sorry train with " + trainNumber + " does not exist!!");
//        }
//        TrainDetails trainDetailsTemp = train.get();
//        trainDetailsTemp.setTrainNumber(trainDetails.getTrainNumber());
//        trainDetailsTemp.setTrainName(trainDetails.getTrainName());
//        trainDetailsTemp.setSourceStation(trainDetails.getSourceStation());
//        trainDetailsTemp.setDestinationStation(trainDetails.getDestinationStation());
//        trainDetailsTemp.setDepartureTime(trainDetails.getDepartureTime());
//        trainDetailsTemp.setArrivalTime(trainDetails.getArrivalTime());
//        trainDetailsTemp.setTrainDepatureDate(trainDetails.getTrainDepatureDate());
//        trainDetailsTemp.setTrainArrivalDate(trainDetails.getTrainArrivalDate());
//        Map<BookingClass, Integer> seatAvailability= trainDetails.getSeatAvailability();
//        trainDetailsTemp.setSeatAvailability(seatAvailability);
//        Map<BookingClass, Integer> fares= trainDetails.getFares();
//        trainDetailsTemp.setFares(fares);
//        log.info("Updating train details for train number: {}", trainNumber);
//        return trainRepository.save(trainDetailsTemp);
//
//    }
//
//    @Override
//    public TrainDetails findTrainByTrainNumber(int trainNumber) throws TrainDetailsNotFoundException {
//        Optional<TrainDetails> train = trainRepository.findByTrainNumber(trainNumber);
//        if (train.isEmpty()) {
//            log.error("Train with train number {} not found", trainNumber);
//            throw new TrainDetailsNotFoundException("Train with train number " + trainNumber + " not found!!");
//        }
//        ;
//        return train.get();
//    }
//
////
//    @Override
//    public String deleteTrainByTrainNumber(int trainNumber) throws TrainDetailsNotFoundException {
//        Optional<TrainDetails> train = trainRepository.findByTrainNumber(trainNumber);
//        if (train.isEmpty()) {
//            log.error("Train with train number {} not found", trainNumber);
//            throw new TrainDetailsNotFoundException("Train with " + trainNumber + " not found!!");
//        }
//        log.info("Deleting train with train number: {}", trainNumber);
//        trainRepository.delete(train.get());
//        return "Train with train number " + trainNumber + " is deleted successfully!";
//    }
//
//    @Override
//    public TrainDetails updateSeatsforBooking(int trainNumber, int noOfPassengers,BookingClass trainClassType) throws TrainDetailsNotFoundException {
//        Optional<TrainDetails> train = trainRepository.findByTrainNumber(trainNumber);
//
//        if (train.isEmpty()) {
//            log.error("Train with train number {} not found for updating seats", trainNumber);
//            throw new TrainDetailsNotFoundException("Train with given train number" + trainNumber + " not found!!");
//        }
//
//        TrainDetails trainDetails = train.get();
//        Map<BookingClass, Integer> seatAvailability= trainDetails.getSeatAvailability();
//
//        switch (trainClassType) {
//            case SLEEPER:
//                if (seatAvailability.containsKey(BookingClass.SLEEPER)) {
//                    Integer seatsInSleeper = seatAvailability.get(BookingClass.SLEEPER);
//                    int finalSleeperSeats = seatsInSleeper - noOfPassengers;
//                    if (finalSleeperSeats < 0) {
//                        seatAvailability.put(BookingClass.SLEEPER, 0);
//                    } else {
//                        seatAvailability.put(BookingClass.SLEEPER, finalSleeperSeats);
//                    }
//                }
//                break;
//            case THIRDAC:
//                if (seatAvailability.containsKey(BookingClass.THIRDAC)) {
//                    Integer seatsInThirdAC = seatAvailability.get(BookingClass.THIRDAC);
//                    int finalSeatsThirdAc = seatsInThirdAC - noOfPassengers;
//                    if (finalSeatsThirdAc < 0) {
//                        seatAvailability.put(BookingClass.THIRDAC, 0);
//                    } else {
//                        seatAvailability.put(BookingClass.THIRDAC, finalSeatsThirdAc);
//                    }
//                }
//                break;
//            case SECONDAC:
//                if (seatAvailability.containsKey(BookingClass.SECONDAC)) {
//                    Integer seatsInSecondAC = seatAvailability.get(BookingClass.SECONDAC);
//                    int finalSeatsSecondAc = seatsInSecondAC - noOfPassengers;
//                    if (finalSeatsSecondAc < 0) {
//                        seatAvailability.put(BookingClass.SECONDAC, 0);
//                    } else {
//                        seatAvailability.put(BookingClass.SECONDAC, finalSeatsSecondAc);
//                    }
//                }
//                break;
//            default:
//                // Handle other cases or provide an error message
//                log.error("Invalid train class type for updating seats");
//                break;
//        }
//        log.info("Updating seat availability for train number: {}", trainNumber);
//        return trainRepository.save(trainDetails);
//}
//
//    @Override
//    public TrainDetails updateSeatsforCancel(int trainNumber, int noOfPassengers, BookingClass trainClassType) throws TrainDetailsNotFoundException {
//        Optional<TrainDetails> train = trainRepository.findByTrainNumber(trainNumber);
//
//        if (train.isEmpty()) {
//            log.error("Train with train number {} not found for updating seats", trainNumber);
//            throw new TrainDetailsNotFoundException("Train with given train number" + trainNumber + " not found!!");
//        }
//
//        TrainDetails trainDetails = train.get();
//        Map<BookingClass, Integer> seatAvailability= trainDetails.getSeatAvailability();
//
//        switch (trainClassType) {
//            case SLEEPER:
//                if (seatAvailability.containsKey(BookingClass.SLEEPER)) {
//                    Integer seatsInSleeper = seatAvailability.get(BookingClass.SLEEPER);
//                    int finalSleeperSeats = seatsInSleeper + noOfPassengers;
//
//                        seatAvailability.put(BookingClass.SLEEPER, finalSleeperSeats);
//                    }
//                break;
//            case THIRDAC:
//                if (seatAvailability.containsKey(BookingClass.THIRDAC)) {
//                    Integer seatsInThirdAC = seatAvailability.get(BookingClass.THIRDAC);
//                    int finalSeatsThirdAc = seatsInThirdAC + noOfPassengers;
//                        seatAvailability.put(BookingClass.THIRDAC, finalSeatsThirdAc);
//                    }
//                break;
//            case SECONDAC:
//                if (seatAvailability.containsKey(BookingClass.SECONDAC)) {
//                    Integer seatsInSecondAC = seatAvailability.get(BookingClass.SECONDAC);
//                    int finalSeatsSecondAc = seatsInSecondAC + noOfPassengers;
//                    seatAvailability.put(BookingClass.SECONDAC, finalSeatsSecondAc);
//                }
//                break;
//            default:
//                // Handle other cases or provide an error message
//                log.error("Invalid train class type for updating seats");
//                break;
//        }
//        log.info("Updating seat availability for train number: {}", trainNumber);
//        return trainRepository.save(trainDetails);
//    }
//    
//    @Override
//	public List<TrainDetails> getalltheTrains() {
//		return trainRepository.findAll();
//	}
//}
package com.trainservice.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.trainservice.enums.BookingClass;
import com.trainservice.exceptions.TrainAlreadyPresent;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainservice.exceptions.TrainDetailsNotFoundException;
import com.trainservice.model.TrainDetails;
import com.trainservice.repository.TrainRepository;

@Service
public class TrainServiceImpl implements TrainService {
    public static final Logger log= LogManager.getLogger(TrainServiceImpl.class);

    @Autowired
    private TrainRepository trainRepository;

    @Override
    public TrainDetails addTrain(TrainDetails trainDetails) throws TrainAlreadyPresent {
        log.info("Adding a new train with train number: {}", trainDetails.getTrainNumber());
        log.info(trainDetails);
        List<TrainDetails> allTrainDetails = trainRepository.findAll();
        for(TrainDetails train: allTrainDetails){
            if(train.getTrainNumber()==trainDetails.getTrainNumber()){
                log.error("Train with train number {} already present", trainDetails.getTrainNumber());
                throw new TrainAlreadyPresent("Train with train number "+trainDetails.getTrainNumber()+" already present ");
            }
        }

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        //Date is entered from front end in the form of string
//        LocalDate localDepatureDate = LocalDate.parse(trainDetails.getDepatureDate(), formatter);
//        java.sql.Date sqlDate1 = java.sql.Date.valueOf(localDepatureDate);
//        java.util.Date utilDate1 = new java.util.Date(sqlDate1.getTime());
//        trainDetails.setTrainDepatureDate(utilDate1);
//
//        LocalDate localArrivalDate = LocalDate.parse(trainDetails.getArrivalDate(), formatter);
//        java.sql.Date sqlDate2 = java.sql.Date.valueOf(localArrivalDate);
//        java.util.Date utilDate2 = new java.util.Date(sqlDate2.getTime());
//        trainDetails.setTrainArrivalDate(utilDate2);

log.info(trainDetails);
        log.info("Saving the new train with train number: {}", trainDetails.getTrainNumber());
        return trainRepository.save(trainDetails);
    }
    @Override
    public List<TrainDetails> trainBetweenStationsandDate(String sourceStation, String destinationStation,
                                                         String trainDepatureDate) {
        log.info("Finding trains between stations {} and {} on date: {}", sourceStation, destinationStation, trainDepatureDate);
        List<TrainDetails> trains = trainRepository.findAll();
        List<TrainDetails> filteredTrains = new ArrayList<TrainDetails>();
     
        for (TrainDetails train : trains) {
            if (train.getSourceStation().equals(sourceStation) &&
                train.getDestinationStation().equals(destinationStation) &&
                train.getTrainDepatureDate().equals(trainDepatureDate)) {
                filteredTrains.add(train);
            }
        }
     
        return filteredTrains;
    }

    @Override
    public TrainDetails updateTrainDetailsByTrainNumber(int trainNumber, TrainDetails trainDetails) throws TrainDetailsNotFoundException {
        Optional<TrainDetails> train = trainRepository.findByTrainNumber(trainNumber);
        if (train.isEmpty()) {
            log.error("Train with train number {} not found for update", trainNumber);
            throw new TrainDetailsNotFoundException("Sorry train with " + trainNumber + " does not exist!!");
        }
        TrainDetails trainDetailsTemp = train.get();
        trainDetailsTemp.setTrainNumber(trainDetails.getTrainNumber());
        trainDetailsTemp.setTrainName(trainDetails.getTrainName());
        trainDetailsTemp.setSourceStation(trainDetails.getSourceStation());
        trainDetailsTemp.setDestinationStation(trainDetails.getDestinationStation());
        trainDetailsTemp.setDepartureTime(trainDetails.getDepartureTime());
        trainDetailsTemp.setArrivalTime(trainDetails.getArrivalTime());
        trainDetailsTemp.setTrainDepatureDate(trainDetails.getTrainDepatureDate());
        trainDetailsTemp.setTrainArrivalDate(trainDetails.getTrainArrivalDate());
        Map<BookingClass, Integer> seatAvailability= trainDetails.getSeatAvailability();
        trainDetailsTemp.setSeatAvailability(seatAvailability);
        Map<BookingClass, Integer> fares= trainDetails.getFares();
        trainDetailsTemp.setFares(fares);
        log.info("Updating train details for train number: {}", trainNumber);
        return trainRepository.save(trainDetailsTemp);

    }

    @Override
    public TrainDetails findTrainByTrainNumber(int trainNumber) throws TrainDetailsNotFoundException {
        Optional<TrainDetails> train = trainRepository.findByTrainNumber(trainNumber);
        if (train.isEmpty()) {
            log.error("Train with train number {} not found", trainNumber);
            throw new TrainDetailsNotFoundException("Train with train number " + trainNumber + " not found!!");
        }
        ;
        return train.get();
    }

//
    @Override
    public String deleteTrainByTrainNumber(int trainNumber) throws TrainDetailsNotFoundException {
        Optional<TrainDetails> train = trainRepository.findByTrainNumber(trainNumber);
        if (train.isEmpty()) {
            log.error("Train with train number {} not found", trainNumber);
            throw new TrainDetailsNotFoundException("Train with " + trainNumber + " not found!!");
        }
        log.info("Deleting train with train number: {}", trainNumber);
        trainRepository.delete(train.get());
        return "Train with train number " + trainNumber + " is deleted successfully!";
    }

    @Override
    public TrainDetails updateSeatsforBooking(int trainNumber, int noOfPassengers,BookingClass trainClassType) throws TrainDetailsNotFoundException {
        Optional<TrainDetails> train = trainRepository.findByTrainNumber(trainNumber);

        if (train.isEmpty()) {
            log.error("Train with train number {} not found for updating seats", trainNumber);
            throw new TrainDetailsNotFoundException("Train with given train number" + trainNumber + " not found!!");
        }

        TrainDetails trainDetails = train.get();
        Map<BookingClass, Integer> seatAvailability= trainDetails.getSeatAvailability();

        switch (trainClassType) {
            case SLEEPER:
                if (seatAvailability.containsKey(BookingClass.SLEEPER)) {
                    Integer seatsInSleeper = seatAvailability.get(BookingClass.SLEEPER);
                    int finalSleeperSeats = seatsInSleeper - noOfPassengers;
                    if (finalSleeperSeats < 0) {
                        seatAvailability.put(BookingClass.SLEEPER, 0);
                    } else {
                        seatAvailability.put(BookingClass.SLEEPER, finalSleeperSeats);
                    }
                }
                break;
            case THIRDAC:
                if (seatAvailability.containsKey(BookingClass.THIRDAC)) {
                    Integer seatsInThirdAC = seatAvailability.get(BookingClass.THIRDAC);
                    int finalSeatsThirdAc = seatsInThirdAC - noOfPassengers;
                    if (finalSeatsThirdAc < 0) {
                        seatAvailability.put(BookingClass.THIRDAC, 0);
                    } else {
                        seatAvailability.put(BookingClass.THIRDAC, finalSeatsThirdAc);
                    }
                }
                break;
            case SECONDAC:
                if (seatAvailability.containsKey(BookingClass.SECONDAC)) {
                    Integer seatsInSecondAC = seatAvailability.get(BookingClass.SECONDAC);
                    int finalSeatsSecondAc = seatsInSecondAC - noOfPassengers;
                    if (finalSeatsSecondAc < 0) {
                        seatAvailability.put(BookingClass.SECONDAC, 0);
                    } else {
                        seatAvailability.put(BookingClass.SECONDAC, finalSeatsSecondAc);
                    }
                }
                break;
            default:
                // Handle other cases or provide an error message
                log.error("Invalid train class type for updating seats");
                break;
        }
        log.info("Updating seat availability for train number: {}", trainNumber);
        return trainRepository.save(trainDetails);
}

    @Override
    public TrainDetails updateSeatsforCancel(int trainNumber, int noOfPassengers, BookingClass trainClassType) throws TrainDetailsNotFoundException {
        Optional<TrainDetails> train = trainRepository.findByTrainNumber(trainNumber);

        if (train.isEmpty()) {
            log.error("Train with train number {} not found for updating seats", trainNumber);
            throw new TrainDetailsNotFoundException("Train with given train number" + trainNumber + " not found!!");
        }

        TrainDetails trainDetails = train.get();
        Map<BookingClass, Integer> seatAvailability= trainDetails.getSeatAvailability();

        switch (trainClassType) {
            case SLEEPER:
                if (seatAvailability.containsKey(BookingClass.SLEEPER)) {
                    Integer seatsInSleeper = seatAvailability.get(BookingClass.SLEEPER);
                    int finalSleeperSeats = seatsInSleeper + noOfPassengers;

                        seatAvailability.put(BookingClass.SLEEPER, finalSleeperSeats);
                    }
                break;
            case THIRDAC:
                if (seatAvailability.containsKey(BookingClass.THIRDAC)) {
                    Integer seatsInThirdAC = seatAvailability.get(BookingClass.THIRDAC);
                    int finalSeatsThirdAc = seatsInThirdAC + noOfPassengers;
                        seatAvailability.put(BookingClass.THIRDAC, finalSeatsThirdAc);
                    }
                break;
            case SECONDAC:
                if (seatAvailability.containsKey(BookingClass.SECONDAC)) {
                    Integer seatsInSecondAC = seatAvailability.get(BookingClass.SECONDAC);
                    int finalSeatsSecondAc = seatsInSecondAC + noOfPassengers;
                    seatAvailability.put(BookingClass.SECONDAC, finalSeatsSecondAc);
                }
                break;
            default:
                // Handle other cases or provide an error message
                log.error("Invalid train class type for updating seats");
                break;
        }
        log.info("Updating seat availability for train number: {}", trainNumber);
        return trainRepository.save(trainDetails);
    }
    @Override
    public List<TrainDetails> getalltheTrains() {
        
        return trainRepository.findAll();
    }
    @Override
    public List<String> lisOfDestinationStations() {
        List<String> listOfDestinationStations=new ArrayList<>();
        List<TrainDetails> allTrains = trainRepository.findAll();
        for(TrainDetails train:allTrains){
            String destinationStation = train.getDestinationStation();
            listOfDestinationStations.add(destinationStation);
        }
        return  listOfDestinationStations;
    }
    @Override
    public List<String> lisOfSourceStations() {
        List<String> listOfSourceStations=new ArrayList<>();
        List<TrainDetails> allTrains = trainRepository.findAll();
        for(TrainDetails train:allTrains){
            String sourceStation = train.getSourceStation();
            listOfSourceStations.add(sourceStation);
        }
        return  listOfSourceStations;
    }
}