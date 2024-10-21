package com.example.primeNumber.service;

import com.example.primeNumber.exceptions.NegativeNumberException;
import com.example.primeNumber.service.impl.PrimeNumberServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.primeNumber.enums.Algorithm.TRIAL_DIVISION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PrimeNumberServiceTest {

    @InjectMocks
    private PrimeNumberServiceImpl primeNumberService;

    @Test
    public void testThrowsException() {
        int negativeInput = -5;

        assertThrows(NegativeNumberException.class, () -> {
            primeNumberService.getPrimeNumbers(negativeInput, TRIAL_DIVISION);
        });
    }
    @Test
    public void testNullAlgorithm() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            primeNumberService.getPrimeNumbers(10, null);  // Simulate passing null or an invalid algorithm
        });

        assertEquals("Algorithm cannot be null.", exception.getMessage());
    }
}
