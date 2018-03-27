package com.switchfully.order.service.orders;

import com.switchfully.order.domain.customers.Customer;
import com.switchfully.order.domain.orders.Order;
import com.switchfully.order.domain.orders.OrderRepository;
import com.switchfully.order.infrastructure.exceptions.EntityNotFoundException;
import com.switchfully.order.service.customers.CustomerService;
import com.switchfully.order.service.items.ItemService;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class OrderService {

    private CustomerService customerService;
    private ItemService itemService;
    private OrderRepository orderRepository;
    private OrderValidator orderValidator;

    @Inject
    public OrderService(CustomerService customerService, ItemService itemService, OrderRepository orderRepository,
                        OrderValidator orderValidator) {
        this.customerService = customerService;
        this.itemService = itemService;
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
    }

    public Order createOrder(Order order) {
        if (!orderValidator.isValidForCreation(order)) {orderValidator.throwInvalidStateException(order, "creation");}
        if (!doesCustomerExist(order)) {throw new EntityNotFoundException("creation of a new order", Customer.class, order.getCustomerId());}
        // Search for valid customer
        // Search for valid item...? Or, in Mapper?
        return orderRepository.save(order);
    }

    private boolean doesCustomerExist(Order order) {
        return customerService.getCustomer(order.getCustomerId()) != null;
    }


}
