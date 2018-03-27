package com.switchfully.order.domain.orders;

import com.switchfully.order.domain.items.prices.Price;
import org.junit.Test;

import java.math.BigDecimal;

import static com.switchfully.order.domain.orders.orderitems.OrderItemTestBuilder.anOrderItem;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {

    @Test
    public void getTotalPrice_givenOrderWithOrderItems_thenTotalPriceIsSumOfPricesOfOrderItems() {
        Order order = OrderTestBuilder.anOrder()
                .withOrderItems(anOrderItem().withItemPrice(Price.create(BigDecimal.valueOf(40.50))).build(),
                        anOrderItem().withItemPrice(Price.create(BigDecimal.valueOf(60.50))).build(),
                        anOrderItem().withItemPrice(Price.create(BigDecimal.valueOf(25))).build())
                .build();

        Price totalPrice = order.getTotalPrice();

        assertThat(totalPrice.getAmount()
                .equals(new BigDecimal(40.50).add(new BigDecimal(60.50)).add(new BigDecimal(25))))
                .isTrue();
    }

}