package com.example.carsaturdayattempt.customer;

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

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    private CustomerController customerController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        customerController = new CustomerController(customerService);
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = new CustomerBuilder().withName("John").withEmail("john@example.com").build();
        when(customerService.createCustomer(customer)).thenReturn(customer);

        ResponseEntity<Customer> response = customerController.createCustomer(customer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
        verify(customerService, times(1)).createCustomer(customer);
    }

    @Test
    public void testGetCustomerById() {
        Long id = 1L;
        Customer customer = new CustomerBuilder().withId(id).withName("John").withEmail("john@example.com").build();
        when(customerService.findCustomerById(id)).thenReturn(customer);

        ResponseEntity<Customer> response = customerController.getCustomerById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
        verify(customerService, times(1)).findCustomerById(id);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(
                new CustomerBuilder().withName("John").withEmail("john@example.com").build(),
                new CustomerBuilder().withName("Alice").withEmail("alice@example.com").build()
        );
        when(customerService.getAllCustomers()).thenReturn(customers);

        ResponseEntity<List<Customer>> response = customerController.getAllCustomers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
        verify(customerService, times(1)).getAllCustomers();
    }

    @Test
    public void testUpdateCustomer() {
        Long id = 1L;
        Customer customer = new CustomerBuilder().withName("John").withEmail("john@example.com").build();
        when(customerService.updateCustomer(customer)).thenReturn(customer);

        ResponseEntity<Customer> response = customerController.updateCustomer(id, customer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
        verify(customerService, times(1)).updateCustomer(customer);
    }

    @Test
    public void testDeleteCustomer() {
        Long id = 1L;

        ResponseEntity<Void> response = customerController.deleteCustomer(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(customerService, times(1)).deleteCustomer(id);
    }
}
