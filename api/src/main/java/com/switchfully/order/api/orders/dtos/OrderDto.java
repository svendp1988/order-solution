package com.switchfully.order.api.orders.dtos;

import java.util.Arrays;
import java.util.List;

public class OrderDto {

    private List<ItemGroupDto> itemGroups;
    private String customerId;

    public OrderDto() {
    }

    public OrderDto withItemGroups(ItemGroupDto... itemGroups) {
        this.itemGroups = Arrays.asList(itemGroups);
        return this;
    }

    public OrderDto withCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public List<ItemGroupDto> getItemGroups() {
        return itemGroups;
    }

    public String getCustomerId() {
        return customerId;
    }
}
