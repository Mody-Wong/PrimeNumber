package com.example.primeNumber.service;

import com.example.primeNumber.exceptions.NegativeNumberException;
import com.example.primeNumber.response.PrimeNumberResponse;
import com.example.primeNumber.service.impl.PrimeNumberServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.primeNumber.enums.Algorithm.TRIAL_DIVISION;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PrimeNumberInterfaceTest {

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
    public void testZeroAndOneReturnsEmpty() {
        PrimeNumberResponse responseZero = primeNumberService.getPrimeNumbers(0, TRIAL_DIVISION);
        assertTrue(responseZero.getPrimeNumber().isEmpty());

        PrimeNumberResponse responseOne = primeNumberService.getPrimeNumbers(1, TRIAL_DIVISION);
        assertTrue(responseOne.getPrimeNumber().isEmpty());
    }

    //Test floating point?
    //Test
}
