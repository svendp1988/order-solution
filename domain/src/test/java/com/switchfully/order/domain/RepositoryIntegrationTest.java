package com.switchfully.order.domain;

import com.switchfully.order.IntegrationTest;
import com.switchfully.order.domain.customers.Customer;
import com.switchfully.order.domain.customers.CustomerDatabase;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import javax.inject.Inject;

import static com.switchfully.order.domain.customers.CustomerTestBuilder.aCustomer;

public class RepositoryIntegrationTest extends IntegrationTest {

    @Inject
    private Repository<Customer, CustomerDatabase> repository;

    @Test
    public void save() {
        Customer customerToSave = aCustomer().build();

        Customer savedCustomer = repository.save(customerToSave);

        Assertions.assertThat(savedCustomer.getId()).isNotNull();
        Assertions.assertThat(repository.get(savedCustomer.getId()))
                .isEqualToComparingFieldByField(savedCustomer);
    }

    @Test
    public void get() {
        Customer savedCustomer = repository.save(aCustomer().build());

        Customer actualCustomer = repository.get(savedCustomer.getId());

        Assertions.assertThat(actualCustomer)
                .isEqualToComparingFieldByField(savedCustomer);
    }

}