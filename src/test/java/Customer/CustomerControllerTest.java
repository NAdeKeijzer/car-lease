package Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerController = new CustomerController(customerService);
    }

    @Test
    void testCreateCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1L);
        when(customerService.createCustomer(customer)).thenReturn(customer);

        // Act
        ResponseEntity<Customer> responseEntity = customerController.createCustomer(customer);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(customer, responseEntity.getBody());
    }

    @Test
    void testGetCustomerById() {
        // Arrange
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        when(customerService.findCustomerById(customerId)).thenReturn(customer);

        // Act
        ResponseEntity<Customer> responseEntity = customerController.getCustomerById(customerId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(customer, responseEntity.getBody());
    }

    @Test
    void testGetAllCustomers() {
        // Arrange
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());
        when(customerService.getAllCustomers()).thenReturn(customers);

        // Act
        ResponseEntity<List<Customer>> responseEntity = customerController.getAllCustomers();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(customers, responseEntity.getBody());
    }

    @Test
    void testUpdateCustomer() {
        // Arrange
        Long customerId = 1L;
        // Using CustomerBuilder to create a customer with specific attributes
        Customer originalCustomer = new Customer();
        originalCustomer.setId(customerId);
        originalCustomer.setName("Jane Smith");
        originalCustomer.setPhoneNumber("9876543210");

        // Create a modified version of the original customer
        Customer modifiedCustomer = new Customer();
        modifiedCustomer.setId(customerId);
        modifiedCustomer.setName("Jane Doe"); // Change the name
        modifiedCustomer.setPhoneNumber("0123456789"); // Change the phone number

        // Mocking the behavior of customerService.updateCustomer() to return the modified customer
        when(customerService.updateCustomer(modifiedCustomer)).thenReturn(modifiedCustomer);

        // Act
        ResponseEntity<Customer> responseEntity = customerController.updateCustomer(customerId, modifiedCustomer);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(modifiedCustomer, responseEntity.getBody());
        // Assert that the name and phone number have been changed
        assertEquals("Jane Doe", modifiedCustomer.getName());
        assertEquals("0123456789", modifiedCustomer.getPhoneNumber());
    }

    @Test
    void testDeleteCustomer() {
        // Arrange
        Long customerId = 1L;
        doNothing().when(customerService).deleteCustomer(customerId);

        // Act
        ResponseEntity<Void> responseEntity = customerController.deleteCustomer(customerId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(customerService, times(1)).deleteCustomer(customerId);
    }
}
