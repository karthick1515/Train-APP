package com.securityservice.dto;

import com.securityservice.enums.BookingClass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public  class BookingResponseDTO {
    private long  pnrNo;
    private int trainNo;
    private String trainName;
    private String sourceStation;
    private String destinationStation;
    private Date journeyDate;

    private int passengerNo;
    private BookingClass classType;
    private String BookingStatus;
    private int totalFare;
    private List<Passenger> pass;
}