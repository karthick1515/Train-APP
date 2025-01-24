package com.trainservice.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NotFoundTest {
	
	 @Test
	    public void testDefaultConstructor() {
	        TrainDetailsNotFoundException exception = new TrainDetailsNotFoundException();
//	    assertEquals("Train details not found.", exception.getMessage());
	    }

	    @Test
	    public void testMessageConstructor() {
	        String message = "Train details not found.";
	        TrainDetailsNotFoundException exception = new TrainDetailsNotFoundException(message);
	        assertEquals(message, exception.getMessage());
	    }

}
