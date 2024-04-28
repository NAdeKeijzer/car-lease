package com.example.carsaturdayattempt.car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    private CarService carService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        carService = new CarService(carRepository);
    }

    @Test
    public void testCreateCar_ValidCar() {
        Car car = new CarBuilder().withMake("Toyota").withModel("Corolla").withVersion("XL").withNumberOfDoors(4).withEmission(100).withGrossPrice(20000).withNettPrice(18000).build();
        when(carRepository.save(car)).thenReturn(car);

        Car createdCar = carService.createCar(car);

        assertEquals(car, createdCar);
        verify(carRepository, times(1)).save(car);
    }

    @Test
    public void testCreateCar_InvalidCar() {
        Car car = new CarBuilder().withMake("Toyota").withModel("Corolla").build(); // Missing required fields
        assertThrows(IllegalArgumentException.class, () -> carService.createCar(car));
        verify(carRepository, never()).save(car);
    }

    @Test
    public void testDeleteCar() {
        Long id = 1L;
        doNothing().when(carRepository).deleteById(id);

        carService.deleteCar(id);

        verify(carRepository, times(1)).deleteById(id);
    }

    @Test
    public void testUpdateCar_ValidCar() {
        Car car = new CarBuilder().withMake("Toyota").withModel("Corolla").withVersion("XL").withNumberOfDoors(4).withEmission(100).withGrossPrice(20000).withNettPrice(18000).build();
        when(carRepository.save(car)).thenReturn(car);

        Car updatedCar = carService.updateCar(car);

        assertEquals(car, updatedCar);
        verify(carRepository, times(1)).save(car);
    }

    @Test
    public void testUpdateCar_InvalidCar() {
        Car car = new CarBuilder().withMake("Toyota").withModel("Corolla").build(); // Missing required fields
        assertThrows(IllegalArgumentException.class, () -> carService.updateCar(car));
        verify(carRepository, never()).save(car);
    }

    @Test
    public void testFindCarById_ExistingCar() {
        Long id = 1L;
        Car car = new CarBuilder().withId(id).withMake("Toyota").withModel("Corolla").build();
        when(carRepository.findById(id)).thenReturn(Optional.of(car));

        Car foundCar = carService.findCarById(id);

        assertEquals(car, foundCar);
        verify(carRepository, times(1)).findById(id);
    }

    @Test
    public void testFindCarById_NonExistingCar() {
        Long id = 1L;
        when(carRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> carService.findCarById(id));
        verify(carRepository, times(1)).findById(id);
    }

    @Test
    public void testGetAllCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new CarBuilder().withId(1L).withMake("Toyota").withModel("Corolla").build());
        cars.add(new CarBuilder().withId(2L).withMake("Honda").withModel("Accord").build());
        when(carRepository.findAll()).thenReturn(cars);

        List<Car> allCars = carService.getAllCars();

        assertEquals(cars, allCars);
        verify(carRepository, times(1)).findAll();
    }
}
