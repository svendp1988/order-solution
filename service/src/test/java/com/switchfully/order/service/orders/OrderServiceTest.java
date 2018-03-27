package com.switchfully.order.service.orders;

import com.switchfully.order.domain.orders.Order;
import com.switchfully.order.domain.orders.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static com.switchfully.order.domain.orders.OrderTestBuilder.anOrder;

public class OrderServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private OrderService orderService;
    private OrderValidator orderValidatorMock;
    private OrderRepository orderRepositoryMock;

    @Before
    public void setupService() {
        orderRepositoryMock = Mockito.mock(OrderRepository.class);
        orderValidatorMock = Mockito.mock(OrderValidator.class);
        orderService = new OrderService(null, null, orderRepositoryMock, orderValidatorMock);
    }

    @Test
    public void createOrder_happyPath() {
        Order order = anOrder().build();
        Mockito.when(orderValidatorMock.isValidForCreation(order)).thenReturn(true);
        Mockito.when(orderRepositoryMock.save(order)).thenReturn(order);

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

}
