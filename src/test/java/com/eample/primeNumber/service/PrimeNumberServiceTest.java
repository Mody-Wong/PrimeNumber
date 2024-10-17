package com.eample.primeNumber.service;

import com.eample.primeNumber.exceptions.NegativeNumberException;
import com.eample.primeNumber.response.PrimeNumberResponse;
import com.eample.primeNumber.service.impl.PrimeNumberServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.eample.primeNumber.enums.Algorithm.TRIAL_DIVISION;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testZeroAndOneReturnsEmpty() {
        PrimeNumberResponse responseZero = primeNumberService.getPrimeNumbers(0, TRIAL_DIVISION);
        assertTrue(responseZero.getPrimeNumber().isEmpty());

        PrimeNumberResponse responseOne = primeNumberService.getPrimeNumbers(1, TRIAL_DIVISION);
        assertTrue(responseOne.getPrimeNumber().isEmpty());
    }

    //Test floating point?
    //Test
}
