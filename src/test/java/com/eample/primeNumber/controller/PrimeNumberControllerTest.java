package com.eample.primeNumber.controller;

import com.eample.primeNumber.response.PrimeNumberResponse;
import com.eample.primeNumber.service.impl.PrimeNumberServiceImpl;
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


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
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

    private final static String input = "130";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(primeNumberController).build();
    }
    @Test
    @DisplayName("Retrieves the prime number array")
    public void GivenInput_ShouldReturnStatusOk() throws Exception {

        PrimeNumberResponse response = PrimeNumberResponse.builder()
                .input(Integer.parseInt(input))
                .build();

        when(primeNumberService.getPrimeNumber(Integer.valueOf(input))).thenReturn(response);

        HttpHeaders headers = new HttpHeaders();
        headers.add("input", input);

        mockMvc.perform(get(primePath)
                        .headers(headers))
                .andExpect(status().isOk());

        verify(primeNumberService, times(1)).getPrimeNumber(any());
    }

    @Test
    @DisplayName("Should return 400 Bad Request when 'input' header is missing")
    public void GivenMissingHeader_ShouldReturn400BadRequest() throws Exception {

        HttpHeaders headers = new HttpHeaders();

        mockMvc.perform(get(primePath)
                        .headers(headers))
                .andExpect(status().isBadRequest());

        verify(primeNumberService, times(0)).getPrimeNumber(any());
    }
}
