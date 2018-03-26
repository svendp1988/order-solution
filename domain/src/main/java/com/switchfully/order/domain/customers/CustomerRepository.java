package com.switchfully.order.domain.customers;

import com.switchfully.order.domain.customers.dummydatabase.CustomerDatabase;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

@Named
public class CustomerRepository {

    private CustomerDatabase customerDatabase;

    @Inject
    public CustomerRepository(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    public Customer save(Customer customer) {
        customer.generateId();
        customerDatabase.storeCustomer(customer);
        return customer;
    }

    public Customer get(UUID customerId) {
        return customerDatabase.getCustomers().get(customerId);
    }

}
