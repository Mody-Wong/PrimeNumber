package com.example.primeNumber.algorithms.impl;

import com.example.primeNumber.algorithms.AlgorithmInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentSieveAlgorithm implements AlgorithmInterface {
    @Override
    public ArrayList<Integer> getPrimeNumberArrayList(Integer input) {

        boolean[] isPrime = new boolean[input + 1];

//         Initialize all entries in isPrime as true
        for (int i = 2; i <= input; i++) {
            isPrime[i] = true;
        }

//        Executor service, finding out how many processors
        int numThreads = Runtime.getRuntime().availableProcessors();
//        System.out.println(numThreads);
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

//         List to hold futures for each thread's task
        List<Future<Void>> futures = new ArrayList<>();

//         Iterate over all primes up to √input
        for (int p = 2; p * p <= input; p++) {
            if (isPrime[p]) {
                // Mark multiples of p in parallel
                int finalP = p;
                futures.add(executor.submit(new PrimeMarkerTask(isPrime, input, finalP)));
            }
        }

        for (Future<Void> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= input; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

//     Callable task to mark non-prime numbers for a given prime p
    private static class PrimeMarkerTask implements Callable<Void> {
        private final boolean[] isPrime;
        private final int input;
        private final int p;

        public PrimeMarkerTask(boolean[] isPrime, int input, int p) {
            this.isPrime = isPrime;
            this.input = input;
            this.p = p;
        }

        @Override
        public Void call() {
            // Printing for curiosity
            System.out.println("Processing prime: " + p + " in thread: " + Thread.currentThread().getName());

            // Mark multiples of p starting from p * p
            for (int i = p * p; i <= input; i += p) {
                isPrime[i] = false;
            }
            return null;
        }
    }
}
