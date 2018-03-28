package com.switchfully.order.api.orders;

import com.switchfully.order.api.orders.dtos.OrderAfterCreationDto;
import com.switchfully.order.api.orders.dtos.OrderCreationDto;
import com.switchfully.order.api.orders.dtos.reports.OrdersReportDto;
import com.switchfully.order.api.orders.dtos.reports.SingleOrderReportDto;
import com.switchfully.order.domain.orders.Order;
import com.switchfully.order.infrastructure.dto.Mapper;

import javax.inject.Named;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.switchfully.order.domain.orders.Order.OrderBuilder.order;

@Named
public class OrderMapper extends Mapper<OrderCreationDto, Order> {

    private OrderItemMapper orderItemMapper;

    public OrderMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public OrderCreationDto toDto(Order order) {
        throw new UnsupportedOperationException("Not yet supported. Will be implemented to view a single Order");
    }

    @Override
    public Order toDomain(OrderCreationDto orderCreationDto) {
        return order()
                .withCustomerId(UUID.fromString(orderCreationDto.getCustomerId()))
                .withOrderItems(orderCreationDto.getItemGroups().stream()
                        .map(itemGroup -> orderItemMapper.toDomain(itemGroup))
                        .collect(Collectors.toList()))
                .build();
    }

    public OrderAfterCreationDto toOrderAfterCreationDto(Order order) {
        return new OrderAfterCreationDto()
                .withOrderId(order.getId().toString())
                .withTotalPrice(order.getTotalPrice().getAmountAsFloat());
    }

    public OrdersReportDto toOrdersReportDto(List<Order> orders) {
        return new OrdersReportDto()
                .withOrders(orders.stream()
                        .map(this::toSingleOrderReportDto)
                        .collect(Collectors.toList()))
                .withTotalPriceOfAllOrders(orders.stream()
                        .map(order -> order.getTotalPrice().getAmountAsFloat())
                        .reduce(0f, (totalPrice1, totalPrice2) -> totalPrice1 + totalPrice2));
    }

    private SingleOrderReportDto toSingleOrderReportDto(Order order) {
        return new SingleOrderReportDto()
                .withOrderId(order.getId().toString())
                .withItemGroups(order.getOrderItems().stream()
                        .map(orderItem -> orderItemMapper.toItemGroupReportDto(orderItem))
                        .collect(Collectors.toList()));
    }
}
