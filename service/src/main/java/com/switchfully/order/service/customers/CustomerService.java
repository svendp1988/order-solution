package com.switchfully.order.service.customers;

import com.switchfully.order.domain.customers.Customer;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CustomerService {

    private CustomerRepository customerRepository;

    @Inject
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

}
