package com.example.carsaturdayattempt.car;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String make;
    @NotEmpty
    private String model;
    @NotEmpty
    private String version;
    @NotEmpty
    private int numberOfDoors;
    private int emission;
    @NotEmpty
    private int grossPrice;
    @NotEmpty
    private int nettPrice;

}

