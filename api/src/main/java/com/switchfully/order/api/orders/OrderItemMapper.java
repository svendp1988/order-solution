package com.switchfully.order.api.orders;

import com.switchfully.order.api.orders.dtos.ItemGroupDto;
import com.switchfully.order.domain.items.Item;
import com.switchfully.order.domain.orders.orderitems.OrderItem;
import com.switchfully.order.infrastructure.dto.Mapper;
import com.switchfully.order.infrastructure.exceptions.EntityNotFoundException;
import com.switchfully.order.service.items.ItemService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

import static com.switchfully.order.domain.orders.orderitems.OrderItem.OrderItemBuilder.orderItem;

@Named
public class OrderItemMapper extends Mapper<ItemGroupDto, OrderItem> {

    private ItemService itemService;

    @Inject
    public OrderItemMapper(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public ItemGroupDto toDto(OrderItem orderItem) {
        return new ItemGroupDto()
                .withItemId(orderItem.getItemId().toString())
                .withOrderedAmount(orderItem.getOrderedAmount());
    }

    @Override
    public OrderItem toDomain(ItemGroupDto itemGroupDto) {
        return orderItem()
                .withItemId(UUID.fromString(itemGroupDto.getItemId()))
                .withOrderedAmount(itemGroupDto.getOrderedAmount())
                .withItemPrice(getItemForId(itemGroupDto.getItemId()).getPrice())
                .withShippingDateBasedOnAvailableItemStock(getItemForId(itemGroupDto.getItemId()).getAmountOfStock())
                .build();
    }

    private Item getItemForId(String itemIdAsString) {
        Item item = itemService.getItem(UUID.fromString(itemIdAsString));
        if (item == null) {
            throw new EntityNotFoundException("mapping to an order of an item group (for creating a new order)",
                    Item.class, UUID.fromString(itemIdAsString));
        }
        return item;
    }
}
