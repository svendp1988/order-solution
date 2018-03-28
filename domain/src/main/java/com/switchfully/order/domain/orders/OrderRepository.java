package com.switchfully.order.domain.orders;

import com.switchfully.order.domain.Repository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
public class OrderRepository extends Repository<Order, OrderDatabase> {

    @Inject
    public OrderRepository(OrderDatabase database) {
        super(database);
    }

    public List<Order> getOrdersForCustomer(UUID customerId) {
        return getDatabase().getAll().values().stream()
                .filter(order -> order.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }
}
