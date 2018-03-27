package com.switchfully.order.service.items;

import com.switchfully.order.domain.items.Item;
import com.switchfully.order.domain.items.ItemRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ItemService {

    private ItemRepository itemRepository;
    private ItemValidator itemValidator;

    @Inject
    public ItemService(ItemRepository itemRepository, ItemValidator itemValidator) {
        this.itemRepository = itemRepository;
        this.itemValidator = itemValidator;
    }

    public Item createItem(Item item) {
        if (!itemValidator.isValidForCreation(item)) {
            throwInvalidStateException(item, "creation");
        }
        return itemRepository.save(item);
    }

    public Item updateItem(Item item) {
        if (!itemValidator.isValidForUpdating(item)) {
            throwInvalidStateException(item, "updating");
        }
        return itemRepository.update(item);
    }

    private void throwInvalidStateException(Item item, String type) {
        throw new IllegalStateException("Invalid Item provided for " + type + ". Provided object: "
                + (item == null ? null : item.toString()));
    }
}
