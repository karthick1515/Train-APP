package com.securityservice.dto;

import com.securityservice.enums.BookingClass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class BookingDetailsDTO {
    private  String bookingId;
    private String userName;
    private Long pnrNo;
    private int trainNo;

    private String trainName;
    private String sourceStation;

    private List<Passenger> passengers;
    private String destinationStation;
    private Date journeyDate;
    private int totalFare;

    private String date;
    private Long passengerPhoneNumber;

    private BookingClass classType;
    private String BookingStatus;
    private int passengerNo;

}
