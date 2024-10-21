package com.example.primeNumber.response;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrimeNumberResponse {

    private int input;
    private ArrayList<Integer> primeNumbers;
}
