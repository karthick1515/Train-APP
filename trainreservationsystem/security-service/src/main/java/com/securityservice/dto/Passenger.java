package com.securityservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Passenger{
    private String passengerId;
    private String name;
    private int age;
    private String gender;
    private long  pnrNo;
    private String bookingId;
}
