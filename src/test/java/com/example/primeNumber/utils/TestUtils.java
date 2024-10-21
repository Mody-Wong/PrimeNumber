package com.example.primeNumber.utils;

import com.example.primeNumber.response.PrimeNumberResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestUtils {

    public static List<Integer> loadExpectedPrimeNumbersFromJson(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        PrimeNumberResponse primeNumberJson = objectMapper.readValue(new File(filePath), PrimeNumberResponse.class);
        return primeNumberJson.getPrimeNumbers();
    }
}
