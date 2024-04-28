package com.example.carlease.leaserate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeaseRateCalculatorTest {
    @Test
    public void testCalculateLeaseRate() {
        // Test data
        double mileage = 12000;
        int duration = 36;
        double interestRate = 4.5;
        double nettPrice = 25000;

        // Expected result
        double expectedLeaseRate = ((mileage / 12) * duration / nettPrice) + ((interestRate / 100) * nettPrice / 12);

        // Calculate actual result
        double actualLeaseRate = LeaseRateCalculator.calculateLeaseRate(mileage, duration, interestRate, nettPrice);

        // Assert
        assertEquals(expectedLeaseRate, actualLeaseRate, 0.001); // tolerance for floating-point comparison
    }
}
