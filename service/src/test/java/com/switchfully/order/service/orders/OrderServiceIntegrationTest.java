package com.switchfully.order.service.orders;

import com.switchfully.order.IntegrationTest;
import com.switchfully.order.domain.orders.Order;
import com.switchfully.order.domain.orders.OrderRepository;
import com.switchfully.order.domain.orders.OrderTestBuilder;
import org.junit.After;
import org.junit.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceIntegrationTest extends IntegrationTest{

    @Inject
    private OrderService orderService;

    @Inject
    private OrderRepository orderRepository;

    @After
    public void resetDatabase() {
        orderRepository.reset();
    }

    @Test
    public void createOrder() {
        Order orderToCreate = OrderTestBuilder.anOrder().build();

        Order createdOrder = orderService.createOrder(orderToCreate);

        assertThat(createdOrder.getId()).isNotNull();
        assertThat(createdOrder).isEqualToComparingFieldByFieldRecursively(orderToCreate);
    }

}