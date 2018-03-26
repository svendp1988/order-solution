package com.switchfully.order.service.customers.dummydatabase;

import com.switchfully.order.domain.customers.Customer;

import javax.inject.Named;
import java.util.*;
import java.util.stream.Collectors;

@Named
public class CustomerDatabase {

    private Map<UUID, Customer> customers;

    public CustomerDatabase() {
        customers = new HashMap<>();
    }

    public void poplulate(Customer... customers) {
        this.customers = Arrays.stream(customers)
                .collect(Collectors.toMap(Customer::getId, customer -> customer));
    }

    public Map<UUID, Customer> getCustomers() {
        return Collections.unmodifiableMap(customers);
    }

    public void storeCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }
}
