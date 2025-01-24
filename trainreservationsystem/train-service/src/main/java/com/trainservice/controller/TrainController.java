//package com.trainservice.controller;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.List;
//
//import com.trainservice.enums.BookingClass;
//import com.trainservice.exceptions.TrainAlreadyPresent;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.trainservice.exceptions.TrainDetailsNotFoundException;
//import com.trainservice.model.TrainDetails;
//import com.trainservice.service.TrainService;
//
//@CrossOrigin("*")
//@RestController
//@RequestMapping("/admin")
//public class TrainController {
//
//	@Autowired
//	private TrainService trainService;
//
//	@PostMapping("/addTrain")
//	ResponseEntity<TrainDetails> addTrain(@Valid @RequestBody  TrainDetails trainDetails) throws TrainAlreadyPresent {
//		return new ResponseEntity<TrainDetails>(trainService.addTrain(trainDetails), HttpStatus.CREATED);
//	}
//
//
//	@GetMapping("/getTrainBetweenStationAndDate/{sourceStation}/{destinationStation}/{trainDepatureDate}")
//	ResponseEntity<List<TrainDetails>> trainBetweenStationsandDate(@PathVariable String sourceStation,
//																   @PathVariable String destinationStation, @PathVariable String trainDepatureDate) {
//
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate localDate = LocalDate.parse(trainDepatureDate, formatter);
//		java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
//		java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
//
//		return new ResponseEntity<List<TrainDetails>>(
//				trainService.trainBetweenStationsandDate(sourceStation, destinationStation, utilDate), HttpStatus.OK);
//	}
//
//	@PutMapping("/updateTrain/{trainNumber}")
//	ResponseEntity<TrainDetails> updateTrainDetailsByTrainNumber(@PathVariable int trainNumber,
//															  @Valid @RequestBody TrainDetails trainDetails) throws TrainDetailsNotFoundException {
//		return new ResponseEntity<TrainDetails>(trainService.updateTrainDetailsByTrainNumber(trainNumber, trainDetails),
//				HttpStatus.OK);
//	}
//
//	@GetMapping("/search/{trainNumber}")
//	ResponseEntity<TrainDetails> findTrainByTrainNumber(@PathVariable int trainNumber)
//			throws TrainDetailsNotFoundException {
//		return new ResponseEntity<TrainDetails>(trainService.findTrainByTrainNumber(trainNumber), HttpStatus.OK);
//	}
//
//	@DeleteMapping("/delete/{trainNumber}")
//	ResponseEntity<String> deleteTrainByTrainNumber(@PathVariable int trainNumber)
//			throws TrainDetailsNotFoundException {
//		return new ResponseEntity<String>(trainService.deleteTrainByTrainNumber(trainNumber), HttpStatus.OK);
//	}
//
//	@PutMapping("/updateSeatsforBooking/{trainNumber}/{noOfPassengers}/{classType}")
//	ResponseEntity<TrainDetails> updateSeatsForBooking(@PathVariable int trainNumber,@PathVariable int noOfPassengers,@PathVariable BookingClass classType) throws TrainDetailsNotFoundException {
//		return new ResponseEntity<TrainDetails>(trainService.updateSeatsforBooking(trainNumber,noOfPassengers,classType),HttpStatus.OK);
//	}
//
//	@PutMapping("/updateSeatsforCancel/{trainNumber}/{noOfPassengers}/{classType}")
//	ResponseEntity<TrainDetails> updateSeatsForCancel(@PathVariable int trainNumber,@PathVariable int noOfPassengers,@PathVariable BookingClass classType) throws TrainDetailsNotFoundException {
//		return new ResponseEntity<TrainDetails>(trainService.updateSeatsforCancel(trainNumber,noOfPassengers,classType),HttpStatus.OK);
//	}
//	
//	@GetMapping("/getalltrain")
//	ResponseEntity<List<TrainDetails>> getalltrain(){
//		return new ResponseEntity<List<TrainDetails>>(trainService.getalltheTrains(), HttpStatus.OK);
//	}
//
//
//}
package com.trainservice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.trainservice.enums.BookingClass;
import com.trainservice.exceptions.TrainAlreadyPresent;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.trainservice.exceptions.TrainDetailsNotFoundException;
import com.trainservice.model.TrainDetails;
import com.trainservice.service.TrainService;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping("/addTrain")
    ResponseEntity<TrainDetails> addTrain(@Valid @RequestBody  TrainDetails trainDetails) throws TrainAlreadyPresent {
        return new ResponseEntity<TrainDetails>(trainService.addTrain(trainDetails), HttpStatus.CREATED);
    }


    @GetMapping("/getTrainBetweenStationAndDate/{sourceStation}/{destinationStation}/{DepatureDate}")
    public ResponseEntity<List<TrainDetails>> trainBetweenStationsandDate(
            @PathVariable String sourceStation,
            @PathVariable String destinationStation,
            @PathVariable String DepatureDate) {
        // Define the formatter for the input date
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     
        // Parse the input date string
        LocalDate localDate = LocalDate.parse(DepatureDate, inputFormatter);
     
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneOffset.UTC);
     
        // Format the ZonedDateTime as a String
        String formattedDate = zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
     
        System.out.println(formattedDate);
     
        return new ResponseEntity<>(trainService.trainBetweenStationsandDate(sourceStation, destinationStation, formattedDate), HttpStatus.OK);
    }



    @PutMapping("/updateTrain/{trainNumber}")
    ResponseEntity<TrainDetails> updateTrainDetailsByTrainNumber(@PathVariable int trainNumber,
                                                              @Valid @RequestBody TrainDetails trainDetails) throws TrainDetailsNotFoundException {
        return new ResponseEntity<TrainDetails>(trainService.updateTrainDetailsByTrainNumber(trainNumber, trainDetails),
                HttpStatus.OK);
    }

    @GetMapping("/search/{trainNumber}")
    ResponseEntity<TrainDetails> findTrainByTrainNumber(@PathVariable int trainNumber)
            throws TrainDetailsNotFoundException {
        return new ResponseEntity<TrainDetails>(trainService.findTrainByTrainNumber(trainNumber), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{trainNumber}")
    ResponseEntity<String> deleteTrainByTrainNumber(@PathVariable int trainNumber)
            throws TrainDetailsNotFoundException {
        return new ResponseEntity<String>(trainService.deleteTrainByTrainNumber(trainNumber), HttpStatus.OK);
    }

    @PutMapping("/updateSeatsforBooking/{trainNumber}/{noOfPassengers}/{classType}")
    ResponseEntity<TrainDetails> updateSeatsForBooking(@PathVariable int trainNumber,@PathVariable int noOfPassengers,@PathVariable BookingClass classType) throws TrainDetailsNotFoundException {
        return new ResponseEntity<TrainDetails>(trainService.updateSeatsforBooking(trainNumber,noOfPassengers,classType),HttpStatus.OK);
    }

    @PutMapping("/updateSeatsforCancel/{trainNumber}/{noOfPassengers}/{classType}")
    ResponseEntity<TrainDetails> updateSeatsForCancel(@PathVariable int trainNumber,@PathVariable int noOfPassengers,@PathVariable BookingClass classType) throws TrainDetailsNotFoundException {
        return new ResponseEntity<TrainDetails>(trainService.updateSeatsforCancel(trainNumber,noOfPassengers,classType),HttpStatus.OK);
    }

    @GetMapping("/getalltrain")
    ResponseEntity<List<TrainDetails>> getalltrain(){
        return new ResponseEntity<List<TrainDetails>>(trainService.getalltheTrains(), HttpStatus.OK);
    }
    @GetMapping("/allDestinationStations")
	ResponseEntity<List<String>> listOFDestinationStations(){
		return  new ResponseEntity<List<String>>(trainService.lisOfDestinationStations(),HttpStatus.OK);
 
	}
	@GetMapping("/allSourceStations")
	ResponseEntity<List<String>> listOFSourceStations(){
		return  new ResponseEntity<List<String>>(trainService.lisOfSourceStations(),HttpStatus.OK);
 
	}
	

}
