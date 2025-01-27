package com.bookingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookingservice.dto.BookingResponseDTO;
import com.bookingservice.exceptions.BookingDetailsNotFoundException;
import com.bookingservice.model.BookingDetails;
import com.bookingservice.model.Passenger;
import com.bookingservice.service.BookingService;

import jakarta.validation.Valid;
@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class BookingController {

    @Autowired
   private BookingService bookingService;

    @GetMapping("/allBookingDetailsOfUser/{userName}")
    public ResponseEntity<List<BookingDetails>> getAllDetailsForUser(@PathVariable String userName) throws BookingDetailsNotFoundException {
        return  new ResponseEntity<List<BookingDetails>>(bookingService.getAllDetailsForUser(userName), HttpStatus.OK);
    }

    @GetMapping("/getDetailsByPnrNo/{pnrNo}")
    public ResponseEntity<BookingDetails> getUserDetailsByPnr(@PathVariable long pnrNo) throws BookingDetailsNotFoundException{
        return  new  ResponseEntity<BookingDetails>(bookingService.getUserDetailsByPnr(pnrNo),HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<BookingResponseDTO> addUserBookingDetails(@Valid @RequestBody BookingDetails userDetails){
        return  new ResponseEntity<BookingResponseDTO>(bookingService.addUserBookingDetails(userDetails),HttpStatus.OK);
    }

    @PutMapping("/cancel/{pnrNo}")
    public ResponseEntity<String> cancelUserBookingDetails( @PathVariable long pnrNo) throws BookingDetailsNotFoundException{
        return new ResponseEntity<String>(bookingService.cancelUserBookingDetails(pnrNo),HttpStatus.OK);
    }
    
    @GetMapping("/getallbookingdetails")
	ResponseEntity<List<BookingDetails>> getalltrain(){
		return new ResponseEntity<List<BookingDetails>>(bookingService.getAll(), HttpStatus.OK);
	}
    
    @GetMapping("/getPassengerDetailsByPnrNo/{pnrNo}")
    public ResponseEntity<List<Passenger>> getPassengerDetailsByPnr(@PathVariable long pnrNo) throws BookingDetailsNotFoundException{
        return  new  ResponseEntity<List<Passenger>>(bookingService.getPassengerDetailsByPnr(pnrNo),HttpStatus.OK);
    }
    @GetMapping("/admin/getPassengerDetails")
    public ResponseEntity<List<Passenger>> getAllPassengerDetails(){
    	return new ResponseEntity<List<Passenger>>(bookingService.getallPassenger(),HttpStatus.OK);
    }

}
