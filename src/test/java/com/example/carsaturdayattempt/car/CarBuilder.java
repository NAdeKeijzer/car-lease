package com.example.carsaturdayattempt.car;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CarBuilder {
    private Long id;
    private String make;
    private String model;
    private String version;
    private int numberOfDoors;
    private int emission;
    private int grossPrice;
    private int nettPrice;

    public CarBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CarBuilder withMake(String make) {
        this.make = make;
        return this;
    }

    public CarBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public CarBuilder withVersion(String version) {
        this.version = version;
        return this;
    }

    public CarBuilder withNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
        return this;
    }

    public CarBuilder withEmission(int emission) {
        this.emission = emission;
        return this;
    }

    public CarBuilder withGrossPrice(int grossPrice) {
        this.grossPrice = grossPrice;
        return this;
    }

    public CarBuilder withNettPrice(int nettPrice) {
        this.nettPrice = nettPrice;
        return this;
    }

    public Car build() {
        Car car = new Car();
        car.setId(this.id);
        car.setMake(this.make);
        car.setModel(this.model);
        car.setVersion(this.version);
        car.setNumberOfDoors(this.numberOfDoors);
        car.setEmission(this.emission);
        car.setGrossPrice(this.grossPrice);
        car.setNettPrice(this.nettPrice);
        return car;
    }
}
