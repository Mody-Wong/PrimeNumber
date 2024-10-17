package com.example.primeNumber.controller;

import com.example.primeNumber.enums.Algorithm;
import com.example.primeNumber.response.PrimeNumberResponse;
import com.example.primeNumber.service.impl.PrimeNumberServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor

public class PrimeNumberController {

    @Autowired
    private PrimeNumberServiceImpl primeNumberService;

    @Operation(
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = PrimeNumberResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Failed", content = @Content)
            }

    )

    @GetMapping(value = "/primes", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PrimeNumberResponse> getPrimeNumber(

        @Schema(type = "integer", defaultValue = "10")
        @RequestHeader(name = "input") int input,

        @Parameter(
            description = "Algorithm to calculate prime numbers",
            schema = @Schema(implementation = Algorithm.class),
            example = "TRIAL_DIVISION"
        )
        @RequestHeader(name = "algorithm", defaultValue = "trialDivision") Algorithm algorithm
    ){
        return ResponseEntity.ok(primeNumberService.getPrimeNumbers(input, algorithm));
    }
}
