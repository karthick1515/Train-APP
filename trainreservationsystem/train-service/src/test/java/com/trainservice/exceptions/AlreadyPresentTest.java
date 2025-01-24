package com.trainservice.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class AlreadyPresentTest {

    @Test
    public void testDefaultConstructor() {
        TrainAlreadyPresent exception = new TrainAlreadyPresent();
//    assertEquals("Train is already present.", exception.getMessage());
    }

    @Test
    public void testMessageConstructor() {
        String message = "Train is already present.";
        TrainAlreadyPresent exception = new TrainAlreadyPresent(message);
        assertEquals(message, exception.getMessage());
    }
}
