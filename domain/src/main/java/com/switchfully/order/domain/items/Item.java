package com.switchfully.order.domain.items;

import com.switchfully.order.domain.Entity;
import com.switchfully.order.domain.items.prices.Price;
import com.switchfully.order.infrastructure.builder.Builder;

public class Item extends Entity {

    private String name;
    private String description;
    private Price price;
    private int amountOfStock;

    public Item(ItemBuilder itemBuilder) {
        name = itemBuilder.name;
        description = itemBuilder.description;
        price = itemBuilder.price;
        amountOfStock = itemBuilder.amountOfStock;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Price getPrice() {
        return price;
    }

    public int getAmountOfStock() {
        return amountOfStock;
    }

    public static class ItemBuilder extends Builder<Item> {

        private String name;
        private String description;
        private Price price;
        private int amountOfStock;

        public static ItemBuilder item() {
            return new ItemBuilder();
        }

        @Override
        public Item build() {
            return new Item(this);
        }

        public ItemBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ItemBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ItemBuilder withPrice(Price price) {
            this.price = price;
            return this;
        }

        public ItemBuilder withAmountOfStock(int amountOfStock) {
            this.amountOfStock = amountOfStock;
            return this;
        }
    }
}
