package com.securityservice.client;

import com.securityservice.dto.BookingDetailsDTO;
import com.securityservice.dto.BookingResponseDTO;
import com.securityservice.dto.TrainDetailsDTO;
import com.securityservice.exception.CustomErrorDecoder;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "BOOKING-SERVICE",url = "http://localhost:9091", configuration = CustomErrorDecoder.class)
public interface BookingClient {
    @GetMapping("/user/allBookingDetailsOfUser/{userName}")
    List<BookingDetailsDTO> getAllDetailsForUser(@PathVariable String userName);

    @GetMapping("/user/getDetailsByPnrNo/{pnrNo}")
    BookingDetailsDTO getUserDetailsByPnr(@PathVariable long pnrNo);

    @PostMapping("/user/book")
    BookingResponseDTO addUserBookingDetails(@RequestBody BookingDetailsDTO userDetails);

    @PutMapping("/user/cancel/{pnrNo}")
    String cancelUserBookingDetails( @PathVariable long pnrNo);

}
