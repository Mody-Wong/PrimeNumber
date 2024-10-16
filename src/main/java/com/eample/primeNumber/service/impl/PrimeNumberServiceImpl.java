package com.eample.primeNumber.service.impl;

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
    public PrimeNumberResponse getPrimeNumber(Integer input) {

        if(input >= 0) {
            PrimeNumberResponse response = new PrimeNumberResponse();
            response.setPrimeNumber(primeList(input));
            response.setInput(input);
            return response;
        } else {
            throw new NegativeNumberException("Negative numbers are not allowed: " + input);
        }
    }

    private ArrayList<Integer> primeList(Integer input){

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
