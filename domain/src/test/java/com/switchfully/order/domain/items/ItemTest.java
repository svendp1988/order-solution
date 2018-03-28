package com.switchfully.order.domain.items;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static com.switchfully.order.domain.items.ItemTestBuilder.anItem;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void decrementStock() {
        Item item = anItem().withAmountOfStock(10).build();
        item.decrementStock(8);
        assertThat(item.getAmountOfStock()).isEqualTo(2);
    }

    @Test
    public void decrementStock_givenHigherAmountToDecrementThanActualRemainingStock_thenThrowException() {
        UUID itemId = UUID.randomUUID();
        Item item = anItem().withId(itemId).withAmountOfStock(7).build();

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Decrementing the stock amount of an item " + itemId.toString() + " below 0 is not allowed");

        item.decrementStock(8);

    }


}