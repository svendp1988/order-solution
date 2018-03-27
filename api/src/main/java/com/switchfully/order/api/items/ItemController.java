package com.switchfully.order.api.items;

import com.switchfully.order.service.items.ItemService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping(path = "/" + ItemController.RESOURCE_NAME)
public class ItemController {

    public static final String RESOURCE_NAME = "items";

    private ItemService itemService;
    private ItemMapper itemMapper;

    @Inject
    public ItemController(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ItemDto createCustomer(@RequestBody ItemDto itemDto) {
        return itemMapper.toDto(
                itemService.createItem(
                        itemMapper.toDomain(itemDto)));
    }

}
