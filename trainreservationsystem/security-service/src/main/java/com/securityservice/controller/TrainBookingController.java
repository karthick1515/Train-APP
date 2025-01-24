package com.securityservice.controller;

import com.securityservice.client.BookingClient;
import com.securityservice.client.TrainClient;
import com.securityservice.dto.BookingDetailsDTO;
import com.securityservice.dto.BookingResponseDTO;
import com.securityservice.enums.BookingClass;
import com.securityservice.model.UserInfo;
import com.securityservice.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.securityservice.dto.TrainDetailsDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/train/api")
 public class TrainBookingController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TrainClient trainClient;
    @Autowired
    private BookingClient bookingClient;
    
    @Autowired
    private AuthService service;

    @PutMapping("/updateUserProfile/{email}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<UserInfo> updateUserProfile(@PathVariable String email,@RequestBody UserInfo userInfo){
        return new ResponseEntity<UserInfo>(service.updateUserProfile(email,userInfo),HttpStatus.OK);
    }
    
    
    @PostMapping("/addTrain")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<TrainDetailsDTO> addTrain(@RequestBody TrainDetailsDTO trainDetails)  {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        //Date is entered from front end in the form of string
//        LocalDate localDepatureDate = LocalDate.parse(trainDetails.getDepatureDate(), formatter);
//        java.sql.Date sqlDate1 = java.sql.Date.valueOf(localDepatureDate);
//        java.util.Date utilDate1 = new java.util.Date(sqlDate1.getTime());
//        trainDetails.setTrainDepatureDate(utilDate1);
//
//        LocalDate localArrivalDate = LocalDate.parse(trainDetails.getArrivalDate(), formatter);
//        java.sql.Date sqlDate2 = java.sql.Date.valueOf(localDepatureDate);
//        java.util.Date utilDate2 = new java.util.Date(sqlDate2.getTime());
//        trainDetails.setTrainArrivalDate(utilDate2);

       // TrainDetailsDTO trainDetailsDTO = restTemplate.postForObject("http://TRAIN-SERVICE/admin/addTrain", trainDetails, TrainDetailsDTO.class);
        TrainDetailsDTO trainDetailsDTO = trainClient.addTrain(trainDetails);
        return  new ResponseEntity<>(trainDetailsDTO, HttpStatus.OK);
    }

    @GetMapping("/getTrainBetweenStationAndDate/{sourceStation}/{destinationStation}/{trainDepatureDate}")
    ResponseEntity<List<TrainDetailsDTO>> trainBetweenStationsandDate(@PathVariable String sourceStation,
                                                                      @PathVariable String destinationStation, @PathVariable String trainDepatureDate) {
       // TrainDetailsDTO[] arrayOfTrainDetails = restTemplate.getForObject("http://TRAIN-SERVICE/admin/getTrainBetweenStationAndDate/" + sourceStation + "/" + destinationStation + "/" + trainDate, TrainDetailsDTO[].class);
       // List<TrainDetailsDTO> listOfTrainDetails = Arrays.stream(arrayOfTrainDetails).collect(Collectors.toList());
        List<TrainDetailsDTO> listOftrainDetailsDTO = trainClient.trainBetweenStationsandDate(sourceStation, destinationStation, trainDepatureDate);
        return  new ResponseEntity<List<TrainDetailsDTO>>(listOftrainDetailsDTO,HttpStatus.OK);
    }


    @PutMapping("/updateTrain/{trainNumber}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    ResponseEntity<TrainDetailsDTO> updateTrainDetailsByTrainNumber(@PathVariable int trainNumber,
                                                                    @RequestBody TrainDetailsDTO trainDetails)  {
        TrainDetailsDTO trainDetailsDTO = trainClient.updateTrainDetailsByTrainNumber(trainNumber, trainDetails);
        return  new ResponseEntity<TrainDetailsDTO>(trainDetailsDTO,HttpStatus.OK);
    }

    @GetMapping("/search/{trainNumber}")
    ResponseEntity<TrainDetailsDTO> findTrainByTrainNumber(@PathVariable int trainNumber){
     //   TrainDetailsDTO trainDetailsDTO = restTemplate.getForObject("http://TRAIN-SERVICE/admin/search/" + trainNumber, TrainDetailsDTO.class);
        TrainDetailsDTO trainDetailsDTO = trainClient.findTrainByTrainNumber(trainNumber);
        return new ResponseEntity<TrainDetailsDTO>(trainDetailsDTO,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{trainNumber}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    ResponseEntity<String> deleteTrainByTrainNumber(@PathVariable int trainNumber) {
        //restTemplate.delete("http://TRAIN-SERVICE/admin/delete/"+trainNumber,TrainDetailsDTO.class);
        //String message="Train with Train Number "+trainNumber+" is deleted successfully!!";
        String message = trainClient.deleteTrainByTrainNumber(trainNumber);
        return new ResponseEntity<String>(message,HttpStatus.OK);
    }

    @PutMapping("/updateSeatsforBooking/{trainNumber}/{noOfPassengers}/{classType}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    ResponseEntity<TrainDetailsDTO> updateSeatsForBooking(@PathVariable int trainNumber,@PathVariable int noOfPassengers ,@PathVariable BookingClass classType)  {
        TrainDetailsDTO trainDetailsDTO = trainClient.updateSeatsForBooking(trainNumber, noOfPassengers,classType);
        return new ResponseEntity<TrainDetailsDTO>(trainDetailsDTO,HttpStatus.OK);
    }
    
    @PutMapping("/updateSeatforCancel/{trainNumber}/{noOfPassengers}/{classType}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    ResponseEntity<TrainDetailsDTO> updateSeatsForCancel(@PathVariable int trainNumber,@PathVariable int noOfPassengers ,@PathVariable BookingClass classType)  {
        TrainDetailsDTO trainDetailsDTO = trainClient.updateSeatsForCancel(trainNumber, noOfPassengers,classType);
        return new ResponseEntity<TrainDetailsDTO>(trainDetailsDTO,HttpStatus.OK);
    }


    @GetMapping("/allBookingDetailsOfUser/{userName}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BookingDetailsDTO>> getAllDetailsForUser(@PathVariable String userName){
      //  BookingDetailsDTO[] bookingDetailsArray = restTemplate.getForObject("http://BOOKING-SERVICE/user/allBookingDetailsOfUser/" + userName, BookingDetailsDTO[].class);
      // List<BookingDetailsDTO> bookingDetailsList = Arrays.stream(bookingDetailsArray).collect(Collectors.toList());
        List<BookingDetailsDTO> listOfBookingDetails = bookingClient.getAllDetailsForUser(userName);
        return new ResponseEntity<List<BookingDetailsDTO>>(listOfBookingDetails,HttpStatus.OK);
    }

    @GetMapping("/getDetailsByPnrNo/{pnrNo}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<BookingDetailsDTO> getUserDetailsByPnr(@PathVariable long pnrNo) {
     //   BookingDetailsDTO bookingDetailsDTO = restTemplate.getForObject("http://BOOKING-SERVICE/user/getDetailsByPnrNo/" + pnrNo, BookingDetailsDTO.class);
        BookingDetailsDTO userDetailsByPnr = bookingClient.getUserDetailsByPnr(pnrNo);
        return new ResponseEntity<BookingDetailsDTO>(userDetailsByPnr,HttpStatus.OK);
    }

    @PostMapping("/book")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<BookingResponseDTO> addUserBookingDetails(@RequestBody BookingDetailsDTO userDetails){
       // BookingResponseDTO bookingResponseDTO = restTemplate.postForObject("http://BOOKING-SERVICE/user/book", userDetails, BookingResponseDTO.class);
        BookingResponseDTO bookingResponseDTO = bookingClient.addUserBookingDetails(userDetails);
        return new ResponseEntity<BookingResponseDTO>(bookingResponseDTO,HttpStatus.OK);
    }

    @PutMapping("/cancel/{pnrNo}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> cancelUserBookingDetails(@PathVariable long pnrNo) {
      //  restTemplate.put("http://BOOKING-SERVICE/user/cancel/"+pnrNo, BookingDetailsDTO.class);
      //  String message="Ticket with PNR number "+pnrNo+" is deleted successfully!!";
        String message = bookingClient.cancelUserBookingDetails(pnrNo);
        return new ResponseEntity<String>(message,HttpStatus.OK);
    }
    
    @GetMapping("/getalltrain")
	ResponseEntity<List<TrainDetailsDTO>> getalltrain(){
		return new ResponseEntity<List<TrainDetailsDTO>>(trainClient.getalltheTrains(), HttpStatus.OK);
	}

}