package com.switchfully.order.api.items;

import com.switchfully.order.ControllerIntegrationTest;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemControllerIntegrationTest extends ControllerIntegrationTest {

    @Test
    public void createCustomer()  {
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

}