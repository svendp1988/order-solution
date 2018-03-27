package com.switchfully.order.api.items;

import com.switchfully.order.ControllerIntegrationTest;
import com.switchfully.order.domain.items.Item;
import com.switchfully.order.domain.items.ItemRepository;
import com.switchfully.order.domain.items.prices.Price;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import java.math.BigDecimal;

import static com.switchfully.order.domain.items.ItemTestBuilder.anItem;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemControllerIntegrationTest extends ControllerIntegrationTest {

    @Inject
    private ItemRepository itemRepository;

    @Test
    public void createItem() {
        ItemDto itemToCreate = new ItemDto()
                .withName("Half-Life 3")
                .withDescription("Boehoehoe...")
                .withPrice(45.50f)
                .withAmountOfStock(50510);

        ItemDto itemDto = new TestRestTemplate()
                .postForObject(format("http://localhost:%s/%s", getPort(), ItemController.RESOURCE_NAME), itemToCreate, ItemDto.class);

        assertThat(itemDto.getId()).isNotNull().isNotEmpty();
        assertThat(itemDto).isEqualToIgnoringGivenFields(itemToCreate, "id");

    }

    @Test
    public void updateItem() {
        Item alreadyExistingItem = itemRepository.save(anItem()
                .withName("Laptop X10")
                .withDescription("A fancy laptop")
                .withAmountOfStock(15)
                .withPrice(Price.create(BigDecimal.valueOf(1199.95)))
                .build());

        ItemDto itemToUpdate = new ItemDto()
                .withId(alreadyExistingItem.getId())
                .withName(alreadyExistingItem.getName())
                .withDescription("A very fancy laptop")
                .withPrice(alreadyExistingItem.getPrice().getAmountAsFloat())
                .withAmountOfStock(10);

        ResponseEntity<ItemDto> result = new TestRestTemplate()
                .exchange(format("http://localhost:%s/%s", getPort(), ItemController.RESOURCE_NAME),
                        HttpMethod.PUT,
                        new HttpEntity<>(itemToUpdate),
                        ItemDto.class);

        assertThat(result.getBody()).isEqualToComparingFieldByField(itemToUpdate);

    }

}