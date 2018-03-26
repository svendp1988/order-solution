package com.switchfully.order.domain.customers;

import com.switchfully.order.domain.customers.addresses.Address;
import com.switchfully.order.domain.customers.emails.Email;
import com.switchfully.order.domain.customers.phonenumbers.PhoneNumber;
import com.switchfully.order.infrastructure.builder.Builder;

import java.util.UUID;

public class Customer {

    private UUID id;
    private String firstname;
    private String lastname;
    private Email email;
    private Address address;
    private PhoneNumber phoneNumber;

    private Customer(CustomerBuilder customerBuilder) {
        this.firstname = customerBuilder.firstname;
        this.lastname = customerBuilder.lastname;
        this.email = customerBuilder.email;
        this.address = customerBuilder.address;
        this.phoneNumber = customerBuilder.phoneNumber;
    }

    public void generateId() throws IllegalStateException {
        if (id != null) {
            throw new IllegalStateException("Generating an ID for a customer that already has " +
                    "an ID (" + id + ") is not allowed.");
        }
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public static class CustomerBuilder extends Builder<Customer> {

        private String firstname;
        private String lastname;
        private Email email;
        private Address address;
        private PhoneNumber phoneNumber;

        private CustomerBuilder() {
        }

        public static CustomerBuilder customer() {
            return new CustomerBuilder();
        }

        @Override
        public Customer build() {
            return new Customer(this);
        }

        public CustomerBuilder withFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public CustomerBuilder withLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public CustomerBuilder withEmail(Email email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public CustomerBuilder withPhoneNumber(PhoneNumber phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

    }
}
