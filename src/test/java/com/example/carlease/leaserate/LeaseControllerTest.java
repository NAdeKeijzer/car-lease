package com.example.carlease.leaserate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeaseControllerTest {

    private LeaseRateController leaseRateController;

    @BeforeEach
    public void setup() {
        leaseRateController = new LeaseRateController();
    }

    @Test
    public void testCreateCustomer() {
        LeaseRateRequest request = new LeaseRateRequest(66000, 36, 3.5, 55000);

        ResponseEntity<Double> response = leaseRateController.calculateLeaseRate(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        DecimalFormat df = new DecimalFormat("#.##");
        assertEquals(df.format(164.02), df.format(response.getBody()));
    }
}
