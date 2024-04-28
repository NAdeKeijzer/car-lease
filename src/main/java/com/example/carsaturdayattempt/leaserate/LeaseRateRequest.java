package com.example.carsaturdayattempt.leaserate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeaseRateRequest {
    private int mileage;
    private int duration;
    private double interestRate;
    private int nettPrice;
}