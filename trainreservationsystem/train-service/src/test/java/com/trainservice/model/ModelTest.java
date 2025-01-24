package com.trainservice.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.trainservice.enums.BookingClass;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ModelTest {
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testGetterAndSetterMethods() {
        TrainDetails trainDetails = new TrainDetails();

        // Set values using setters
        trainDetails.setTrainNumber(123);
        trainDetails.setTrainName("Test Train");
        trainDetails.setSourceStation("Source");
        trainDetails.setDestinationStation("Destination");
        trainDetails.setDepartureTime("10:00 AM");
        trainDetails.setArrivalTime("12:00 PM");
        trainDetails.setTrainDepatureDate(new Date());
        trainDetails.setTrainArrivalDate(new Date());
        trainDetails.setSeatAvailability(Map.of(BookingClass.SECONDAC, 10));
        trainDetails.setFares(Map.of(BookingClass.THIRDAC, 500));

        // Use getters to retrieve values
        assertThat(trainDetails.getTrainNumber()).isEqualTo(123);
        assertThat(trainDetails.getTrainName()).isEqualTo("Test Train");
        assertThat(trainDetails.getSourceStation()).isEqualTo("Source");
        assertThat(trainDetails.getDestinationStation()).isEqualTo("Destination");
        assertThat(trainDetails.getDepartureTime()).isEqualTo("10:00 AM");
        assertThat(trainDetails.getArrivalTime()).isEqualTo("12:00 PM");
        assertThat(trainDetails.getTrainDepatureDate()).isInstanceOf(Date.class);
        assertThat(trainDetails.getTrainArrivalDate()).isInstanceOf(Date.class);
        assertThat(trainDetails.getSeatAvailability()).isEqualTo(Map.of(BookingClass.SECONDAC, 10));
        assertThat(trainDetails.getFares()).isEqualTo(Map.of(BookingClass.THIRDAC, 500));
    }

    @Test
    public void testValidationConstraints() {
        TrainDetails trainDetails = new TrainDetails();

        // Validate constraints using the Bean Validation API
        Set<ConstraintViolation<TrainDetails>> violations = validator.validate(trainDetails);
        assertThat(violations).isNotEmpty();

        // Add more test cases for specific constraints and scenarios
    }

    @Test
    public void testValidationConstraintsWithInvalidData() {
        TrainDetails trainDetails = new TrainDetails();
        // Set invalid data to violate constraints
        trainDetails.setTrainNumber(0);
        trainDetails.setTrainName("");
        trainDetails.setSourceStation("");
        trainDetails.setDestinationStation("");
        // ... Set more invalid data

        // Validate constraints with invalid data
        Set<ConstraintViolation<TrainDetails>> violations = validator.validate(trainDetails);
        assertThat(violations).isNotEmpty();

        // You can use assertThatThrownBy to test specific constraints violations
//        assertThatThrownBy(() -> validator.validate(trainDetails))
//                .isInstanceOf(jakarta.validation.ConstraintViolationException.class);
    }
}


