package com.bookingservice.exception;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.bookingservice.exceptions.BookingDetailsNotFoundException;

public class BookingNotFoundTest {
	@Test
    public void testDefaultConstructor() {
        BookingDetailsNotFoundException exception = new BookingDetailsNotFoundException();
//        Assertions.assertEquals("Booking details not found.", exception.getMessage());
    }

    @Test
    public void testMessageConstructor() {
        String message = "Booking details not found.";
        BookingDetailsNotFoundException exception = new BookingDetailsNotFoundException(message);
       Assertions.assertEquals(message, exception.getMessage());
    }
}

