package com.switchfully.order.api.items;

import java.util.UUID;

public class ItemDto {

    private String id;
    private String name;
    private String description;
    private float price;
    private int amountOfStock;

    public ItemDto() {
    }

    public ItemDto withId(UUID id) {
        this.id = id.toString();
        return this;
    }

    public ItemDto withName(String name) {
        this.name = name;
        return this;
    }

    public ItemDto withDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemDto withPrice(float price) {
        this.price = price;
        return this;
    }

    public ItemDto withAmountOfStock(int amountOfStock) {
        this.amountOfStock = amountOfStock;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public int getAmountOfStock() {
        return amountOfStock;
    }
}
