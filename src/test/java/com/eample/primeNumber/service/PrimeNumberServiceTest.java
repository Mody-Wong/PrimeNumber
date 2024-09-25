package com.eample.primeNumber.service;

import com.eample.primeNumber.exceptions.NegativeNumberException;
import com.eample.primeNumber.response.PrimeNumberResponse;
import com.eample.primeNumber.service.impl.PrimeNumberServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
            primeNumberService.getPrimeNumber(negativeInput);
        });
    }

    @Test
    public void testZeroAndOneReturnsEmpty() {
        PrimeNumberResponse responseZero = primeNumberService.getPrimeNumber(0);
        assertTrue(responseZero.getPrimeNumber().isEmpty());

        PrimeNumberResponse responseOne = primeNumberService.getPrimeNumber(1);
        assertTrue(responseOne.getPrimeNumber().isEmpty());
    }

    //Test floating point?
    //Test
}
