package com.switchfully.order.domain.customers;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static com.switchfully.order.domain.customers.CustomerTestBuilder.aCustomer;
import static org.junit.Assert.*;

public class CustomerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void generateId_givenCustomerWithoutId_whenGeneratingId_thenGenerateId() {
        Customer aCustomer = aCustomer().build();
        aCustomer.generateId();
        Assertions.assertThat(aCustomer.getId())
                .isNotNull()
                .isNotEqualTo("");
    }

    @Test
    public void generateId_givenCustomerWithId_whenGeneratingId_thenThrowException() {
        UUID id = UUID.randomUUID();

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Generating an ID for a customer that already has an ID (" + id + ") is not allowed.");

        aCustomer().withId(id).build().generateId();
    }

}