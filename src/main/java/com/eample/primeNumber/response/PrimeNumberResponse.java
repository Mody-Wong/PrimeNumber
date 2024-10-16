package com.eample.primeNumber.response;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PrimeNumberResponse {

    private int input;
    private ArrayList<Integer> primeNumber;
}
