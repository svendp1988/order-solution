package com.switchfully.order.service.items;

import com.switchfully.order.IntegrationTest;
import com.switchfully.order.domain.items.Item;
import com.switchfully.order.domain.items.prices.Price;
import org.junit.Test;

import javax.inject.Inject;
import java.math.BigDecimal;

import static com.switchfully.order.domain.items.ItemTestBuilder.anItem;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemServiceIntegrationTest extends IntegrationTest {

    @Inject
    private ItemService itemService;

    @Test
    public void createItem() {
        Item createdItem = itemService.createItem(anItem()
                .withName("The Martian")
                .withDescription("A cool book written by a software engineer")
                .withAmountOfStock(239)
                .withPrice(Price.create(BigDecimal.valueOf(10.90)))
                .build());

        assertThat(createdItem).isNotNull();
        assertThat(createdItem.getId()).isNotNull().isNotEqualTo("");
        assertThat(createdItem.getName()).isEqualTo("The Martian");
        assertThat(createdItem.getDescription()).isEqualTo("A cool book written by a software engineer");
        assertThat(createdItem.getAmountOfStock()).isEqualTo(239);
        assertThat(createdItem.getPrice().getAmount()).isEqualTo(BigDecimal.valueOf(10.90));

    }

}