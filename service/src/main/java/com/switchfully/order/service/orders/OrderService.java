package com.switchfully.order.service.orders;

import com.switchfully.order.domain.orders.Order;
import com.switchfully.order.domain.orders.OrderRepository;
import com.switchfully.order.service.customers.CustomerService;
import com.switchfully.order.service.items.ItemService;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class OrderService {

    private CustomerService customerService;
    private ItemService itemService;
    private OrderRepository orderRepository;

    @Inject
    public OrderService(CustomerService customerService, ItemService itemService, OrderRepository orderRepository) {
        this.customerService = customerService;
        this.itemService = itemService;
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

}
