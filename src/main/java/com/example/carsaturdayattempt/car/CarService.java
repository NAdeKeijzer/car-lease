package com.example.carsaturdayattempt.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car createCar(Car car) {
        validateCar(car);
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public Car updateCar(Car car) {
        validateCar(car);
        return carRepository.save(car);
    }

    public Car findCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        return optionalCar.orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    private void validateCar(Car car) {
        if (!StringUtils.hasText(car.getMake())) {
            throw new IllegalArgumentException("Make cannot be empty or null");
        }
        if (!StringUtils.hasText(car.getModel())) {
            throw new IllegalArgumentException("Model cannot be empty or null");
        }
        if (!StringUtils.hasText(car.getVersion())) {
            throw new IllegalArgumentException("Version cannot be empty or null");
        }
        if (car.getNumberOfDoors() < 2 || car.getNumberOfDoors() > 5) {
            throw new IllegalArgumentException("Number of doors must be between 2 and 5");
        }
        if (car.getEmission() < 0 || car.getEmission() > 500) {
            throw new IllegalArgumentException("Emission must be between 0 and 500");
        }
        if (car.getGrossPrice() <= 0) {
            throw new IllegalArgumentException("Gross price must be greater than 0");
        }
        if (car.getNettPrice() <= 0) {
            throw new IllegalArgumentException("Nett price must be greater than 0");
        }
    }
}
