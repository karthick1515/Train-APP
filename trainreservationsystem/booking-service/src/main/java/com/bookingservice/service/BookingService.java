package com.bookingservice.service;

import java.util.List;

import com.bookingservice.dto.BookingResponseDTO;
import com.bookingservice.exceptions.BookingDetailsNotFoundException;
import com.bookingservice.model.BookingDetails;
import com.bookingservice.model.Passenger;

public interface BookingService {
    public List<BookingDetails> getAll();

    List<BookingDetails> getAllDetailsForUser(String userName) throws BookingDetailsNotFoundException;

    public BookingDetails getUserDetailsByPnr(long pnrNo) throws BookingDetailsNotFoundException;

    public BookingResponseDTO addUserBookingDetails(BookingDetails userDetails);

    public String cancelUserBookingDetails(long pnrNo) throws BookingDetailsNotFoundException;
    

	public List<Passenger> getPassengerDetailsByPnr(long pnrNo) throws BookingDetailsNotFoundException;
	public List<Passenger> getallPassenger();
}
