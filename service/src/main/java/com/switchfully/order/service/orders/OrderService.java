package com.switchfully.order.service.orders;

import com.switchfully.order.domain.customers.Customer;
import com.switchfully.order.domain.customers.CustomerRepository;
import com.switchfully.order.domain.items.ItemRepository;
import com.switchfully.order.domain.orders.Order;
import com.switchfully.order.domain.orders.OrderRepository;
import com.switchfully.order.domain.orders.orderitems.OrderItem;
import com.switchfully.order.infrastructure.exceptions.EntityNotFoundException;
import com.switchfully.order.infrastructure.exceptions.EntityNotValidException;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;

@Named
public class OrderService {

    private CustomerRepository customerRepository;
    private ItemRepository itemRepository;
    private OrderRepository orderRepository;
    private OrderValidator orderValidator;

    @Inject
    public OrderService(CustomerRepository customerRepository,
                        ItemRepository itemRepository,
                        OrderRepository orderRepository,
                        OrderValidator orderValidator) {
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
    }

    public Order createOrder(Order order) {
        assertOrderIsValidForCreation(order);
        assertOrderingCustomerExists(order);
        assertAllOrderedItemsExist(order);
        return orderRepository.save(order);
    }

    public List<Order> getOrdersForCustomer(UUID customerId) {
        return orderRepository.getOrdersForCustomer(customerId);
    }

    private void assertAllOrderedItemsExist(Order order) {
        if(!doAllOrderItemsReferenceAnExistingItem(order.getOrderItems())) {
            throw new EntityNotValidException("creation of a new order when checking if all the ordered items exist",
                    order);
        }
    }

    private boolean doAllOrderItemsReferenceAnExistingItem(List<OrderItem> orderItems) {
        return orderItems.stream()
                .filter(orderItem -> itemRepository.get(orderItem.getItemId()) == null)
                .map(nonExistingItem -> false)
                .findFirst()
                .orElse(true);
    }

    private void assertOrderingCustomerExists(Order order) {
        if (!doesCustomerExist(order)) {
            throw new EntityNotFoundException("creation of a new order when checking if the referenced customer exists",
                    Customer.class, order.getCustomerId());
        }
    }

    private boolean doesCustomerExist(Order order) {
        return customerRepository.get(order.getCustomerId()) != null;
    }

    private void assertOrderIsValidForCreation(Order order) {
        if (!orderValidator.isValidForCreation(order)) {
            orderValidator.throwInvalidStateException(order, "creation");
        }
    }
}
