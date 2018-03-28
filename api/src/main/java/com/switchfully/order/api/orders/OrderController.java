package com.switchfully.order.api.orders;

import com.switchfully.order.api.orders.dtos.OrderAfterCreationDto;
import com.switchfully.order.api.orders.dtos.OrderCreationDto;
import com.switchfully.order.api.orders.dtos.reports.OrdersReportDto;
import com.switchfully.order.service.orders.OrderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.UUID;

@RestController
@RequestMapping(path = "/" + OrderController.RESOURCE_NAME)
public class OrderController {

    public static final String RESOURCE_NAME = "orders";

    private OrderService orderService;
    private OrderMapper orderMapper;

    @Inject
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrderAfterCreationDto createOrder(@RequestBody OrderCreationDto orderDto) {
        return orderMapper.toOrderAfterCreationDto(
                orderService.createOrder(
                        orderMapper.toDomain(orderDto)));
    }

    @PostMapping(path="/{id}/reorder",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrderAfterCreationDto reorderOrder(@PathVariable String id) {
        return orderMapper.toOrderAfterCreationDto(
                orderService.reorderOrder(UUID.fromString(id)));
    }

    @GetMapping(path ="/customers/{customerId}" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrdersReportDto getOrdersForCustomerReport(@PathVariable String customerId) {
        return orderMapper.toOrdersReportDto(
                orderService.getOrdersForCustomer(UUID.fromString(customerId)));
    }

}
