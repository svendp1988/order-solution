package com.switchfully.order.domain.orders;

import com.switchfully.order.domain.Repository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class OrderRepository extends Repository<Order, OrderDatabase> {

    @Inject
    public OrderRepository(OrderDatabase database) {
        super(database);
    }
}
