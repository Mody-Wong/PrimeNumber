package com.eample.primeNumber.controller;

import com.eample.primeNumber.response.PrimeNumberResponse;
import com.eample.primeNumber.service.impl.PrimeNumberServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
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

    @GetMapping("/primes")
    public ResponseEntity<PrimeNumberResponse> getPrimeNumber(
            @Schema(type = "integer", defaultValue = "10")
            @RequestHeader(name = "input") int input
    ){
        return ResponseEntity.ok(primeNumberService.getPrimeNumber(input));
    }
}
