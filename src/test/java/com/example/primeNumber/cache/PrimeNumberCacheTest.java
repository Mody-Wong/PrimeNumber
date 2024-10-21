package com.example.primeNumber.cache;

import com.example.primeNumber.enums.Algorithm;
import com.example.primeNumber.response.PrimeNumberResponse;
import com.example.primeNumber.service.impl.PrimeNumberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class PrimeNumberCacheTest {
    @Autowired
    private PrimeNumberServiceImpl primeNumberService;

    @Test
    public void testCachingForIdenticalInputs() {
        Integer input = 10;
        Algorithm algorithm = Algorithm.SIEVE;

        // First call calculates and caches the result
        PrimeNumberResponse response1 = primeNumberService.getPrimeNumbers(input, algorithm);

        // Second call should retrieve the cached result
        PrimeNumberResponse response2 = primeNumberService.getPrimeNumbers(input, algorithm);

        // Compare object references (should be the same if cached)
        assertSame(response1, response2, "The second call should return the cached response.");
    }

    @Test
    public void testCacheMiss() {
        Integer input = 10;
        Algorithm algorithm1 = Algorithm.SIEVE;
        Algorithm algorithm2 = Algorithm.TRIAL_DIVISION;

        PrimeNumberResponse response1 = primeNumberService.getPrimeNumbers(input, algorithm1);

        PrimeNumberResponse response2 = primeNumberService.getPrimeNumbers(input, algorithm2);

        assertNotSame(response1.getPrimeNumbers(), response2.getPrimeNumbers());
    }
}
