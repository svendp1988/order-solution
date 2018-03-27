package com.switchfully.order.domain.items.prices;

import java.math.BigDecimal;

public final class Price {

    private final BigDecimal amount;

    private Price(BigDecimal amount) {
        this.amount = amount;
    }

    public static Price create(BigDecimal amount) {
        return new Price(amount);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public float getAmountAsFloat() {
        return amount.floatValue();
    }
}
