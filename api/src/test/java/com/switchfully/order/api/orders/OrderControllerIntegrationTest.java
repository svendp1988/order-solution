package com.switchfully.order.api.orders;

import com.switchfully.order.ControllerIntegrationTest;
import com.switchfully.order.api.orders.dtos.ItemGroupDto;
import com.switchfully.order.api.orders.dtos.OrderAfterCreationDto;
import com.switchfully.order.api.orders.dtos.OrderDto;
import com.switchfully.order.domain.customers.Customer;
import com.switchfully.order.domain.customers.CustomerRepository;
import com.switchfully.order.domain.items.Item;
import com.switchfully.order.domain.items.ItemRepository;
import com.switchfully.order.domain.items.prices.Price;
import org.junit.After;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

import javax.inject.Inject;
import java.math.BigDecimal;

import static com.switchfully.order.domain.customers.CustomerTestBuilder.aCustomer;
import static com.switchfully.order.domain.items.ItemTestBuilder.anItem;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderControllerIntegrationTest extends ControllerIntegrationTest {

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private ItemRepository itemRepository;

    @After
    public void resetDatabase() {
        customerRepository.reset();
        itemRepository.reset();
    }

    @Test
    public void createOrder() {
        Customer customer = customerRepository.save(aCustomer().build());
        Item itemOne = itemRepository.save(anItem()
                .withAmountOfStock(10)
                .withPrice(Price.create(BigDecimal.valueOf(10)))
                .build());
        Item itemTwo = itemRepository.save(anItem()
                .withAmountOfStock(4)
                .withPrice(Price.create(BigDecimal.valueOf(2.5)))
                .build());


        OrderDto orderDto = new OrderDto()
                .withCustomerId(customer.getId().toString())
                .withItemGroups(
                        new ItemGroupDto()
                                .withItemId(itemOne.getId().toString())
                                .withOrderedAmount(8),
                        new ItemGroupDto()
                                .withItemId(itemTwo.getId().toString())
                                .withOrderedAmount(5)
                );

        OrderAfterCreationDto orderAfterCreationDto = new TestRestTemplate()
                .postForObject(format("http://localhost:%s/%s", getPort(), OrderController.RESOURCE_NAME), orderDto,
                        OrderAfterCreationDto.class);

        assertThat(orderAfterCreationDto).isNotNull();
        assertThat(orderAfterCreationDto.getOrderId()).isNotNull().isNotEmpty();
        assertThat(orderAfterCreationDto.getTotalPrice()).isEqualTo(92.5f);
    }

}