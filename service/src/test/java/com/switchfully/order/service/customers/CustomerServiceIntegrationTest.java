package com.switchfully.order.service.customers;

import com.switchfully.order.IntegrationTest;
import com.switchfully.order.domain.customers.Customer;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import javax.inject.Inject;

import static com.switchfully.order.domain.customers.CustomerTestBuilder.aCustomer;

public class CustomerServiceIntegrationTest extends IntegrationTest{

    @Inject
    private CustomerService customerService;

    @Inject
    private CustomerRepository customerRepository;

    @Test
    public void createCustomer() {
        Customer customerToCreate = aCustomer().build();

        Customer createdCustomer = customerService.createCustomer(customerToCreate);

        Assertions.assertThat(customerRepository.get(customerToCreate.getId()))
                .isEqualToComparingFieldByField(customerToCreate)
                .isEqualToComparingFieldByField(createdCustomer);
    }

}