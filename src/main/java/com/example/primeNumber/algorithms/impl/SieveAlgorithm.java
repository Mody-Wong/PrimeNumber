package com.example.primeNumber.algorithms.impl;

import com.example.primeNumber.algorithms.AlgorithmInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SieveAlgorithm implements AlgorithmInterface {
    @Override
    public ArrayList<Integer> getPrimeNumberArrayList(Integer input) {

        boolean[] isPrime = new boolean[input + 1];
        // new boolean[] defaults to False in the boolean Array

        for (int i = 2; i <= input; i++){
            isPrime[i] = true;
        }

        for (int p = 2; p * p <= input; p++){
            if (isPrime[p]) {
                // starting at p * p because all smaller p has already been marked by previous iteration
                for (int i = p * p; i <= input; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= input; i++){
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }
}
