package com.example.primeNumber.service;

import com.example.primeNumber.enums.Algorithm;
import com.example.primeNumber.response.PrimeNumberResponse;

public interface PrimeNumberInterface {

    PrimeNumberResponse getPrimeNumbers(Integer number, Algorithm algorithms);
}
