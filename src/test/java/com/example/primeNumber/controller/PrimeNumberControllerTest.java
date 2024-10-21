package com.example.primeNumber.controller;

import com.example.primeNumber.response.PrimeNumberResponse;
import com.example.primeNumber.service.impl.PrimeNumberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import static com.example.primeNumber.enums.Algorithm.TRIAL_DIVISION;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class PrimeNumberControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private Validator validator;

    @InjectMocks
    private PrimeNumberController primeNumberController;

    @Mock
    private PrimeNumberServiceImpl primeNumberService;

    private final static String primePath = "http://localhost:8080/primes";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(primeNumberController)
                .build();
    }

    @Test
    @DisplayName("Retrieves the prime number array")
    public void GivenInput_ShouldReturnStatusOk() throws Exception {

        PrimeNumberResponse response = PrimeNumberResponse.builder()
                .input(Integer.parseInt("100"))
                .build();

        when(primeNumberService.getPrimeNumbers(Integer.valueOf("100"), TRIAL_DIVISION)).thenReturn(response);

        HttpHeaders headers = new HttpHeaders();
        headers.add("input", "100");
        headers.add("algorithm", String.valueOf(TRIAL_DIVISION));

        mockMvc.perform(get(primePath)
                        .headers(headers))
                .andExpect(status().isOk());

        verify(primeNumberService, times(1)).getPrimeNumbers(any(), any());
    }

    @Test
    @DisplayName("Should return 400 Bad Request when 'input' header is missing")
    public void GivenMissingInputHeader_ShouldReturn400BadRequest() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("algorithm", String.valueOf(TRIAL_DIVISION));

        mockMvc.perform(get(primePath)
                        .headers(headers))
                .andExpect(status().isBadRequest());

        verify(primeNumberService, times(0)).getPrimeNumbers(any(), any());
    }

    @Test
    @DisplayName("Should return 400 Bad Request when invalid algorithm provided")
    public void GivenInvalidAlgorithm_ShouldReturn400BadRequest() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("input", "100");
        headers.add("algorithm", "INVALID_ALGO");

        mockMvc.perform(get(primePath)
                        .headers(headers))
                .andExpect(status().isBadRequest());

        verify(primeNumberService, times(0)).getPrimeNumbers(any(), any());
    }
}
