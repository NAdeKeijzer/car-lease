package com.example.carsaturdayattempt.leaserate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaseRateController {

    @PostMapping("/calculateLeaseRate")
    public ResponseEntity<Double> calculateLeaseRate(@RequestBody LeaseRateRequest request) {
        double leaseRate = LeaseRateCalculator.calculateLeaseRate(
                request.getMileage(),
                request.getDuration(),
                request.getInterestRate(),
                request.getNettPrice()
        );
        return ResponseEntity.ok(leaseRate);
    }
}
