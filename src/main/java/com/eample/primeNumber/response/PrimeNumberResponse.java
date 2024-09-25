package com.eample.primeNumber.response;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PrimeNumberResponse {

    private int input;
    private ArrayList<Integer> primeNumber;

    private LocalDate sysDate;
}
