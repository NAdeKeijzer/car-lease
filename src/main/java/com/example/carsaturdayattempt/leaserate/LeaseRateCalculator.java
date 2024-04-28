package com.example.carsaturdayattempt.leaserate;

public class LeaseRateCalculator {
    public static double calculateLeaseRate(double mileage, int duration, double interestRate, double nettPrice) {
        return ((mileage / 12) * duration / nettPrice) + ((interestRate / 100) * nettPrice / 12);
    }
}
