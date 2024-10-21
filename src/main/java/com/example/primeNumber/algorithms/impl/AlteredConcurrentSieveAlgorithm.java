package com.example.primeNumber.algorithms.impl;

import com.example.primeNumber.algorithms.AlgorithmInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AlteredConcurrentSieveAlgorithm implements AlgorithmInterface {
    @Override
    public ArrayList<Integer> getPrimeNumberArrayList(Integer input) {

        if (input < 2) {
            return new ArrayList<>();
        }

        int sqrtLimit = (int) Math.sqrt(input);

        // Step 1: Serial computation of primes up to sqrt(limit)
        boolean[] isPrimeUpToSqrt = new boolean[sqrtLimit + 1];
        Arrays.fill(isPrimeUpToSqrt, true);
        isPrimeUpToSqrt[0] = isPrimeUpToSqrt[1] = false; // 0 and 1 are not prime

        List<Integer> primesUpToSqrt = new ArrayList<>();
        for (int i = 2; i <= sqrtLimit; i++) {
            if (isPrimeUpToSqrt[i]) {
                primesUpToSqrt.add(i);
                for (int j = i * i; j <= sqrtLimit; j += i) {
                    isPrimeUpToSqrt[j] = false;
                }
            }
        }

        // Step 2: Parallel computation of primes beyond sqrt(limit)
        boolean[] isPrime = new boolean[input + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false; // 0 and 1 are not prime

        // Create a thread pool
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        List<Callable<Void>> tasks = new ArrayList<>();
        for (int prime : primesUpToSqrt) {
            tasks.add(() -> {
//                System.out.println("Marking multiples of prime: " + prime);

                for (int j = prime * prime; j <= input; j += prime) {
                    isPrime[j] = false;
                }
                return null;
            });
        }

        // Execute all tasks in parallel
        try {
            List<Future<Void>> futures = executor.invokeAll(tasks);
            for (Future<Void> future : futures) {
                future.get(); // Wait for all threads to finish
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        // Collect remaining primes
        ArrayList<Integer> allPrimes = new ArrayList<>(primesUpToSqrt);
        for (int i = sqrtLimit + 1; i <= input; i++) {
            if (isPrime[i]) {
                allPrimes.add(i);
            }
        }

        return allPrimes;
    }
}
