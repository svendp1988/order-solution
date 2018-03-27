package com.switchfully.order.service.items;

import com.switchfully.order.domain.items.Item;
import com.switchfully.order.domain.items.ItemRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ItemService {

    private ItemRepository itemRepository;

    @Inject
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }
}
