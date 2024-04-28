package com.example.carsaturdayattempt.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CarControllerTest {

    @Mock
    private CarService carService;

    private CarController carController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        carController = new CarController(carService);
    }

    @Test
    public void testCreateCar() {
        Car car = new CarBuilder().withMake("Toyota").withModel("Corolla").build();
        when(carService.createCar(car)).thenReturn(car);

        ResponseEntity<Car> response = carController.createCar(car);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(car, response.getBody());
        verify(carService, times(1)).createCar(car);
    }

    @Test
    public void testGetCarById() {
        Long id = 1L;
        Car car = new CarBuilder().withMake("Toyota").withModel("Corolla").build();
        when(carService.findCarById(id)).thenReturn(car);

        ResponseEntity<Car> response = carController.getCarById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(car, response.getBody());
        verify(carService, times(1)).findCarById(id);
    }

    @Test
    public void testGetAllCars() {
        List<Car> cars = Arrays.asList(
                new CarBuilder().withMake("Toyota").withModel("Corolla").build(),
                new CarBuilder().withMake("Honda").withModel("Accord").build()
        );
        when(carService.getAllCars()).thenReturn(cars);

        ResponseEntity<List<Car>> response = carController.getAllCars();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
        verify(carService, times(1)).getAllCars();
    }

    @Test
    public void testUpdateCar() {
        Long id = 1L;
        Car car = new CarBuilder().withMake("Toyota").withModel("Corolla").build();
        car.setId(id);
        when(carService.updateCar(car)).thenReturn(car);

        ResponseEntity<Car> response = carController.updateCar(id, car);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(car, response.getBody());
        verify(carService, times(1)).updateCar(car);
    }

    @Test
    public void testDeleteCar() {
        Long id = 1L;

        ResponseEntity<Void> response = carController.deleteCar(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(carService, times(1)).deleteCar(id);
    }
}
