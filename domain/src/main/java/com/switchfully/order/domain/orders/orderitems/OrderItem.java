package com.switchfully.order.domain.orders.orderitems;

import com.switchfully.order.domain.items.prices.Price;
import com.switchfully.order.infrastructure.builder.Builder;

import java.time.LocalDate;
import java.util.UUID;

/**
 * OrderItem is a fabricated (value) object consisting of the original Item's id and price, enriched with
 * order-specific information (the ordered amount and the shipping date).
 */
public final class OrderItem {

    private final UUID itemId;
    private final Price itemPrice;
    private final int orderedAmount;
    private final LocalDate shippingDate;

    public OrderItem(OrderItemBuilder orderItemBuilder) {
        itemId = orderItemBuilder.itemId;
        itemPrice = orderItemBuilder.itemPrice;
        orderedAmount = orderItemBuilder.orderedAmount;
        shippingDate = calculateShippingDate();
    }

    private LocalDate calculateShippingDate() {
        return null;
    }

    public static class OrderItemBuilder extends Builder<OrderItem> {

        private UUID itemId;
        private Price itemPrice;
        private int orderedAmount;

        @Override
        public OrderItem build() {
            return new OrderItem(this);
        }

        public OrderItemBuilder withItemId(UUID itemId) {
            this.itemId = itemId;
            return this;
        }

        public OrderItemBuilder withItemPrice(Price itemPrice) {
            this.itemPrice = itemPrice;
            return this;
        }

        public OrderItemBuilder withOrderedAmount(int orderedAmount) {
            this.orderedAmount = orderedAmount;
            return this;
        }
    }

}
