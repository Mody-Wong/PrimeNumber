package com.example.primeNumber.algorithms;

import com.example.primeNumber.algorithms.impl.AlteredConcurrentSieveAlgorithm;
import com.example.primeNumber.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class AlteredConcurrentSieveAlgorithmTest {

    @InjectMocks
    private AlteredConcurrentSieveAlgorithm alteredConcurrentSieveAlgorithm;


    @Test
    public void testZeroAndOneReturnsEmpty() {
        ArrayList<Integer> responseZero = alteredConcurrentSieveAlgorithm.getPrimeNumberArrayList(0);
        assertTrue(responseZero.isEmpty());

        ArrayList<Integer> responseOne = alteredConcurrentSieveAlgorithm.getPrimeNumberArrayList(1);
        assertTrue(responseOne.isEmpty());
    }

    @Test
    public void testInputTwoReturnsTwo() {
        ArrayList<Integer> result = alteredConcurrentSieveAlgorithm.getPrimeNumberArrayList(2);
        assertEquals(Arrays.asList(2), result);
    }

    @Test
    public void testSmallInputReturnsCorrectPrimes() throws IOException {
        Integer smallInput = 100;
        ArrayList<Integer> result = alteredConcurrentSieveAlgorithm.getPrimeNumberArrayList(smallInput);

        List<Integer> expectedPrimeNumbers = TestUtils.loadExpectedPrimeNumbersFromJson("src/test/resources/expectedPrimeNumbers_Small.json");

        assertEquals(expectedPrimeNumbers, result);
    }

    @Test
    public void testLargeInputReturnsCorrectPrimes() throws IOException {
        Integer largeInput = 100000;
        ArrayList<Integer> result = alteredConcurrentSieveAlgorithm.getPrimeNumberArrayList(largeInput);

        List<Integer> expectedPrimeNumbers = TestUtils.loadExpectedPrimeNumbersFromJson("src/test/resources/expectedPrimeNumbers_Large.json");

        assertEquals(expectedPrimeNumbers, result);
    }
}
