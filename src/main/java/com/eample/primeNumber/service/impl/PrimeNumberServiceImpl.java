package com.eample.primeNumber.service.impl;

import com.eample.primeNumber.enums.Algorithm;
import com.eample.primeNumber.exceptions.NegativeNumberException;
import com.eample.primeNumber.response.PrimeNumberResponse;
import com.eample.primeNumber.service.PrimeNumberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
@AllArgsConstructor
public class PrimeNumberServiceImpl implements PrimeNumberService {

    @Override
    public PrimeNumberResponse getPrimeNumbers(Integer input, Algorithm algorithm) {

        if (input < 0) {
            throw new NegativeNumberException("Negative numbers are not allowed: " + input);
        }

        PrimeNumberResponse response = new PrimeNumberResponse();
        ArrayList<Integer> primeNumbers;

        // Choose the algorithm based on the enum value
        switch (algorithm) {
            case TRIAL_DIVISION:
                primeNumbers = primeListUsingTrialDivision(input);
                break;
//            case SIEVE:
//                primeNumbers = primeListUsingSieve(input);
//                break;
//            // Add more cases as needed for other algorithms
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        }

        // Set the response data
        response.setPrimeNumber(primeNumbers);
        response.setInput(input);
        return response;
    }

    private ArrayList<Integer> primeListUsingTrialDivision(Integer input){

        ArrayList<Integer> primeNumbers = new ArrayList<>();

        if (input <= 1) {
            return primeNumbers;
        } else {
            primeNumbers.add(2);
            for (int i = 1; 2 * i + 1 <= input; i++) {
                int num = 2 * i + 1;
                boolean isPrime = true;

                for (int j = 2; j * j <= num; j++) {
                    if (num % j == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                    primeNumbers.add(num);
                }
            }
        }
        return primeNumbers;
    }
}
