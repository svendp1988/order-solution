package com.switchfully.order.service.items;

import com.switchfully.order.domain.items.Item;

import javax.inject.Named;

/**
 * Instead of our custom validator(s),
 * we are better of using Javax Validation (we didn't use it here, to not over-complexify the solution).
 * That way, we can indicate on our Domain classes (such as Customer and Item)
 * what fields are required and what the valid values are for each field.
 */
@Named
public class ItemValidator {

    public boolean isValidForCreation(Item item) {
        return !isAFieldEmptyOrNull(item);
    }

    public boolean isValidForUpdating(Item item) {
        return !isAFieldEmptyOrNull(item) && item.getId() != null;
    }

    private boolean isAFieldEmptyOrNull(Item item) {
        return item == null
                || isEmptyOrNull(item.getName())
                || isEmptyOrNull(item.getDescription())
                || item.getAmountOfStock() < 0
                || item.getPrice() == null
                || item.getPrice().getAmountAsFloat() <= 0;
    }

    private boolean isEmptyOrNull(String attribute) {
        return attribute == null || attribute.isEmpty();
    }
}
