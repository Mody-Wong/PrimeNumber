package com.eample.primeNumber.service;

import com.eample.primeNumber.enums.Algorithm;
import com.eample.primeNumber.response.PrimeNumberResponse;

public interface PrimeNumberService {

    PrimeNumberResponse getPrimeNumbers(Integer number, Algorithm algorithms);
}
