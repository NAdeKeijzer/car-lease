package com.example.carsaturdayattempt.customer;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerBuilder {
    private Long id;
    private String name;
    private String street;
    private String houseNumber;
    private String zipcode;
    private String city;
    private String email;
    private String phoneNumber;


    public CustomerBuilder withId(Long customerId) {
        this.id = customerId;
        return this;
    }

    public CustomerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public CustomerBuilder withHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public CustomerBuilder withZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public CustomerBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public CustomerBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Customer build() {
        return new Customer(id, name, street, houseNumber, zipcode, city, email, phoneNumber);
    }
}
