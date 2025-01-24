package com.securityservice.dto;

import com.securityservice.enums.BookingClass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.Map;


@Getter
@Setter
@ToString
public class TrainDetailsDTO {
    private int trainNumber;
    private String trainName;
    private String sourceStation;
    private String destinationStation;
    private String departureTime;
    private String arrivalTime;
    private Date trainDepatureDate;
    private Date trainArrivalDate;
    private Map<BookingClass, Integer> seatAvailability; // Map to store seat availability
    private Map<BookingClass, Integer> fares; // Map to store fare information
//    private String depatureDate;
//    private String arrivalDate;
}