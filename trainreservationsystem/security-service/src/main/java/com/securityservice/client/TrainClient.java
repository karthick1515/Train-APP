package com.securityservice.client;

import com.securityservice.dto.TrainDetailsDTO;
import com.securityservice.enums.BookingClass;
import com.securityservice.exception.CustomErrorDecoder;
//import com.trainservice.model.TrainDetails;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@FeignClient(name = "TRAIN-SERVICE",url = "http://localhost:8082",configuration = CustomErrorDecoder.class)
public interface TrainClient {
    @PostMapping("/admin/addTrain")
    TrainDetailsDTO addTrain(@Valid @RequestBody TrainDetailsDTO trainDetails);

    @GetMapping("/admin/getTrainBetweenStationAndDate/{sourceStation}/{destinationStation}/{trainDepatureDate}")
    List<TrainDetailsDTO> trainBetweenStationsandDate(@PathVariable String sourceStation,
                                                      @PathVariable String destinationStation, @PathVariable String trainDepatureDate);
    @PutMapping("/admin/updateTrain/{trainNumber}")
    TrainDetailsDTO updateTrainDetailsByTrainNumber(@PathVariable int trainNumber,
                                                    @RequestBody TrainDetailsDTO trainDetails);

    @GetMapping("/admin/search/{trainNumber}")
    TrainDetailsDTO findTrainByTrainNumber(@PathVariable int trainNumber);


    @DeleteMapping("/admin/delete/{trainNumber}")
    String deleteTrainByTrainNumber(@PathVariable int trainNumber);

    @PutMapping("/admin/updateSeatsforBooking/{trainNumber}/{noOfPassengers}/{classType}")
    TrainDetailsDTO updateSeatsForBooking(@PathVariable int trainNumber, @PathVariable int noOfPassengers, @PathVariable BookingClass classType);
    @PutMapping("/admin/updateSeatsforCancel/{trainNumber}/{noOfPassengers}/{classType}")
    TrainDetailsDTO updateSeatsForCancel(@PathVariable int trainNumber, @PathVariable int noOfPassengers, @PathVariable BookingClass classType);
    
    @GetMapping("/getalltrain")
    List<TrainDetailsDTO> getalltheTrains(); 
}