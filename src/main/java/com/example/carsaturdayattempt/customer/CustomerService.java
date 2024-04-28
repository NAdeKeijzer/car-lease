package com.example.carsaturdayattempt.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        validateCustomer(customer);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Customer customer) {
        validateCustomer(customer);
        return customerRepository.save(customer);
    }

    public Customer findCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    private void validateCustomer(Customer customer) {
        if (!StringUtils.hasText(customer.getName())) {
            throw new IllegalArgumentException("Name cannot be empty or null");
        }
        if (!StringUtils.hasText(customer.getStreet())) {
            throw new IllegalArgumentException("Street cannot be empty or null");
        }
        if (!StringUtils.hasText(customer.getHouseNumber())) {
            throw new IllegalArgumentException("Street cannot be empty or null");
        }
        if (!StringUtils.hasText(customer.getZipcode())) {
            throw new IllegalArgumentException("Zipcode cannot be empty or null");
        }
        if (!StringUtils.hasText(customer.getCity())) {
            throw new IllegalArgumentException("City cannot be empty or null");
        }
        if (!isValidEmail(customer.getEmail())) {
            throw new IllegalArgumentException("Invalid email address");
        }
        if (!isValidPhoneNumber(customer.getPhoneNumber())) {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }

    private boolean isValidEmail(String email) {
        // Regular expression for basic email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && !phoneNumber.isEmpty() && phoneNumber.length() <= 15;
    }
}
