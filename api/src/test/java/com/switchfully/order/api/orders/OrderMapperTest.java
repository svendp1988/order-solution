package com.switchfully.order.api.orders;

import com.switchfully.order.api.orders.dtos.ItemGroupDto;
import com.switchfully.order.api.orders.dtos.OrderAfterCreationDto;
import com.switchfully.order.api.orders.dtos.OrderDto;
import com.switchfully.order.domain.orders.Order;
import com.switchfully.order.domain.orders.orderitems.OrderItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static com.switchfully.order.domain.orders.OrderTestBuilder.anOrder;
import static com.switchfully.order.domain.orders.orderitems.OrderItemTestBuilder.anOrderItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class OrderMapperTest {

    private OrderItemMapper orderItemMapperMock;
    private OrderMapper orderMapper;

    @Before
    public void setupService() {
        orderItemMapperMock = Mockito.mock(OrderItemMapper.class);
        orderMapper = new OrderMapper(orderItemMapperMock);
    }

    @Test
    public void toDomain() {
        OrderItem orderItem = anOrderItem().build();
        when(orderItemMapperMock.toDomain(any(ItemGroupDto.class))).thenReturn(orderItem);

        String customerId = UUID.randomUUID().toString();
        Order order = orderMapper.toDomain(new OrderDto()
                .withCustomerId(customerId)
                .withItemGroups(new ItemGroupDto()
                                .withItemId(UUID.randomUUID().toString())
                                .withOrderedAmount(5),
                        new ItemGroupDto()
                                .withItemId(UUID.randomUUID().toString())
                                .withOrderedAmount(1)));

        assertThat(order).isNotNull();
        assertThat(order.getCustomerId().toString()).isEqualTo(customerId);
        assertThat(order.getOrderItems()).containsExactlyInAnyOrder(orderItem, orderItem);
    }

    @Test
    public void toOrderAfterCreationDto() {
        Order order = anOrder().withId(UUID.randomUUID()).build();

        OrderAfterCreationDto orderAfterCreationDto = orderMapper.toOrderAfterCreationDto(order);

        assertThat(orderAfterCreationDto).isNotNull();
        assertThat(orderAfterCreationDto.getOrderId()).isEqualTo(order.getId().toString());
        assertThat(orderAfterCreationDto.getTotalPrice()).isEqualTo(order.getTotalPrice().getAmountAsFloat());
    }

}