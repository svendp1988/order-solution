package com.switchfully.order.api.orders;

import com.switchfully.order.api.orders.dtos.OrderAfterCreationDto;
import com.switchfully.order.api.orders.dtos.OrderDto;
import com.switchfully.order.domain.orders.Order;
import com.switchfully.order.infrastructure.dto.Mapper;

import javax.inject.Named;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.switchfully.order.domain.orders.Order.OrderBuilder.order;

@Named
public class OrderMapper extends Mapper<OrderDto, Order>{

    private OrderItemMapper orderItemMapper;

    public OrderMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public OrderDto toDto(Order order) {
        throw new UnsupportedOperationException("Not yet supported. Will be implemented to view a single Order");
    }

    @Override
    public Order toDomain(OrderDto orderDto) {
        return order()
                .withCustomerId(UUID.fromString(orderDto.getCustomerId()))
                .withOrderItems(orderDto.getItemGroups().stream()
                        .map(itemGroup -> orderItemMapper.toDomain(itemGroup))
                        .collect(Collectors.toList()))
                .build();
    }

    public OrderAfterCreationDto toOrderAfterCreationDto(Order order) {
        return new OrderAfterCreationDto()
                .withOrderId(order.getId().toString())
                .withTotalPrice(order.getTotalPrice().getAmountAsFloat());
    }
}
