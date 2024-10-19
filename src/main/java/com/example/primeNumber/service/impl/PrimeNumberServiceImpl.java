package com.example.primeNumber.service.impl;

import com.example.primeNumber.algorithms.AlgorithmInterface;
import com.example.primeNumber.algorithms.impl.ConcurrentSieveAlgorithm;
import com.example.primeNumber.algorithms.impl.SieveAlgorithm;
import com.example.primeNumber.algorithms.impl.AlteredConcurrentSieveAlgorithm;
import com.example.primeNumber.algorithms.impl.TrialDivisionAlgorithm;
import com.example.primeNumber.enums.Algorithm;
import com.example.primeNumber.exceptions.NegativeNumberException;
import com.example.primeNumber.response.PrimeNumberResponse;
import com.example.primeNumber.service.PrimeNumberInterface;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
@AllArgsConstructor
public class PrimeNumberServiceImpl implements PrimeNumberInterface {

    @Override
    public PrimeNumberResponse getPrimeNumbers(Integer input, Algorithm algorithm) {

        if (input < 0) {
            throw new NegativeNumberException("Negative numbers are not allowed: " + input);
        }

        PrimeNumberResponse response = new PrimeNumberResponse();
        ArrayList<Integer> primeNumbers;
        AlgorithmInterface usedAlgorithm;

        // Choose the algorithm based on the enum value
        primeNumbers = switch (algorithm) {
            case TRIAL_DIVISION -> {
                usedAlgorithm = new TrialDivisionAlgorithm();
                yield usedAlgorithm.getPrimeNumberArrayList(input);
            }
            case SIEVE -> {
                usedAlgorithm = new SieveAlgorithm();
                yield usedAlgorithm.getPrimeNumberArrayList(input);
            }
            case CONCURRENT_SIEVE -> {
                usedAlgorithm = new ConcurrentSieveAlgorithm();
                yield usedAlgorithm.getPrimeNumberArrayList(input);
            }
            case ALTERED_CONCURRENT_SIEVE -> {
                usedAlgorithm = new AlteredConcurrentSieveAlgorithm();
                yield usedAlgorithm.getPrimeNumberArrayList(input);
            }
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        };

        // Set the response data
        response.setPrimeNumber(primeNumbers);
        response.setInput(input);
        return response;
    }
}
