package com.switchfully.order.service.orders;

import com.switchfully.order.domain.orders.Order;
import com.switchfully.order.domain.orders.OrderRepository;
import com.switchfully.order.infrastructure.exceptions.EntityNotFoundException;
import com.switchfully.order.service.customers.CustomerService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.UUID;

import static com.switchfully.order.domain.customers.CustomerTestBuilder.aCustomer;
import static com.switchfully.order.domain.orders.OrderTestBuilder.anOrder;
import static org.mockito.Matchers.any;

public class OrderServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private OrderService orderService;
    private OrderValidator orderValidatorMock;
    private OrderRepository orderRepositoryMock;
    private CustomerService customerServiceMock;

    @Before
    public void setupService() {
        orderRepositoryMock = Mockito.mock(OrderRepository.class);
        orderValidatorMock = Mockito.mock(OrderValidator.class);
        customerServiceMock = Mockito.mock(CustomerService.class);
        orderService = new OrderService(customerServiceMock, null, orderRepositoryMock, orderValidatorMock);
    }

    @Test
    public void createOrder_happyPath() {
        Order order = anOrder().build();
        Mockito.when(orderValidatorMock.isValidForCreation(order)).thenReturn(true);
        Mockito.when(orderRepositoryMock.save(order)).thenReturn(order);
        Mockito.when(customerServiceMock.getCustomer(any(UUID.class))).thenReturn(aCustomer().build());

        Order createdOrder = orderService.createOrder(order);

        Assertions.assertThat(createdOrder).isNotNull();
    }

    @Test
    public void createOrder_givenOrderThatIsNotValidForCreation_thenThrowException() {
        Order order = anOrder().build();
        Mockito.when(orderValidatorMock.isValidForCreation(order)).thenReturn(false);
        Mockito.doThrow(IllegalStateException.class).when(orderValidatorMock)
                .throwInvalidStateException(order, "creation");

        expectedException.expect(IllegalStateException.class);

        orderService.createOrder(order);
    }

    @Test
    public void createOrder_customerDoesNotExist() {
        UUID customerId = UUID.randomUUID();
        Order order = anOrder().withCustomerId(customerId).build();
        Mockito.when(orderValidatorMock.isValidForCreation(order)).thenReturn(true);
        Mockito.when(orderRepositoryMock.save(order)).thenReturn(order);
        Mockito.when(customerServiceMock.getCustomer(customerId)).thenReturn(null);

        expectedException.expect(EntityNotFoundException.class);
        expectedException.expectMessage("During creation of a new order, the following entity was not found: Customer with id = " + customerId.toString());

        orderService.createOrder(order);
    }

}
