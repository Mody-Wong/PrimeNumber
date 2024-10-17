package com.example.primeNumber.algorithms.impl;

import com.example.primeNumber.algorithms.AlgorithmInterface;

import java.util.ArrayList;

public class TrialDivisionAlgorithm implements AlgorithmInterface {
    @Override
    public ArrayList<Integer> getPrimeNumberArrayList(Integer input) {
        ArrayList<Integer> primeNumbers = new ArrayList<>();

        if (input <= 1) {
            return primeNumbers;
        } else {
            primeNumbers.add(2);  // Add 2 as the first prime number
            for (int i = 1; 2 * i + 1 <= input; i++) {
                int num = 2 * i + 1;
                boolean isPrime = true;

                // Check divisibility by the already identified prime numbers only
                for (int prime : primeNumbers) {
                    if (prime * prime > num) {
                        break; // No need to check further if prime^2 > num
                    }
                    if (num % prime == 0) {
                        isPrime = false;
                        break;
                    }
                }

                if (isPrime) {
                    primeNumbers.add(num);  // Add the new prime number to the list
                }
            }
        }
        return primeNumbers;
    }
}
