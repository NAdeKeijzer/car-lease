package com.example.carlease.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer validCustomer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        validCustomer = new CustomerBuilder()
                .withName("John Doe")
                .withStreet("123 Street")
                .withHouseNumber("456")
                .withZipcode("12345")
                .withCity("City")
                .withEmail("john@example.com")
                .withPhoneNumber("123456789")
                .build();
    }

    @Test
    void testCreateCustomer_ValidCustomer() {
        when(customerRepository.save(validCustomer)).thenReturn(validCustomer);

        Customer createdCustomer = customerService.createCustomer(validCustomer);

        assertNotNull(createdCustomer);
        assertEquals(validCustomer, createdCustomer);
    }

    @Test
    void testCreateCustomer_InvalidCustomer_NullName() {
        validCustomer.setName(null);

        assertThrows(RuntimeException.class, () -> customerService.createCustomer(validCustomer));
        verify(customerRepository, never()).save(any());
    }

    @Test
    void testCreateCustomer_InvalidCustomer_EmptyEmail() {
        validCustomer.setEmail("");

        assertThrows(RuntimeException.class, () -> customerService.createCustomer(validCustomer));
        verify(customerRepository, never()).save(any());
    }

    @Test
    void testCreateCustomer_InvalidCustomer_InvalidEmailFormat() {
        validCustomer.setEmail("invalid_email");

        assertThrows(RuntimeException.class, () -> customerService.createCustomer(validCustomer));
        verify(customerRepository, never()).save(any());
    }

    @Test
    void testCreateCustomer_InvalidCustomer_TooLongPhoneNumber() {
        validCustomer.setPhoneNumber("123456789012345678901234567890");

        assertThrows(RuntimeException.class, () -> customerService.createCustomer(validCustomer));
        verify(customerRepository, never()).save(any());
    }

    @Test
    void testFindCustomerById_CustomerNotFound() {
        long nonExistingCustomerId = 999L;

        when(customerRepository.findById(nonExistingCustomerId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> customerService.findCustomerById(nonExistingCustomerId));
    }

    @Test
    void testUpdateCustomer_InvalidCustomer_NullName() {
        validCustomer.setName(null);

        assertThrows(IllegalArgumentException.class, () -> customerService.updateCustomer(validCustomer));
        verify(customerRepository, never()).save(any());
    }

    @Test
    void testDeleteCustomer_CustomerNotFound() {
        long nonExistingCustomerId = 999L;

        doThrow(new RuntimeException("Customer not found")).when(customerRepository).deleteById(nonExistingCustomerId);

        assertThrows(RuntimeException.class, () -> customerService.deleteCustomer(nonExistingCustomerId));
    }
}
