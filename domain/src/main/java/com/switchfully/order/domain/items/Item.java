package com.switchfully.order.domain.items;

import com.switchfully.order.domain.Entity;
import com.switchfully.order.infrastructure.builder.Builder;

public class Item extends Entity {

    private String name;
    private String description;
    private int amountOfStock;

    public Item(ItemBuilder itemBuilder) {
        name = itemBuilder.name;
        description = itemBuilder.description;
        amountOfStock = itemBuilder.amountOfStock;
    }

    public static class ItemBuilder extends Builder<Item> {

        private String name;
        private String description;
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

        public ItemBuilder withAmountOfStock(int amountOfStock) {
            this.amountOfStock = amountOfStock;
            return this;
        }
    }
}
