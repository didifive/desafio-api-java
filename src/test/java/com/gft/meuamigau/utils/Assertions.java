package com.gft.meuamigau.utils;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.opentest4j.MultipleFailuresError;

public class Assertions {
	
	public static void assertThrowsExceptionWithCorrectMessage(Runnable method
    		, Class<?> expectedClass
    		, String expectedMessage) throws MultipleFailuresError {
		
		try {
			method.run();
			fail("Function run without throw exception");
		}catch (Exception e) {
			assertAll(
	                "Check if throw exception with correct message"
	                , () -> assertEquals(expectedClass, e.getClass())
	                , () -> assertEquals(expectedMessage, e.getMessage())
	        );
		}
        
	}
	
}
