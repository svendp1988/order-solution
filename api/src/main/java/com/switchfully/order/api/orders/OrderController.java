package com.switchfully.order.api.orders;

import com.switchfully.order.api.orders.dtos.OrderAfterCreationDto;
import com.switchfully.order.api.orders.dtos.OrderDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/" + OrderController.RESOURCE_NAME)
public class OrderController {

    public static final String RESOURCE_NAME = "orders";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrderAfterCreationDto createOrder(@RequestBody OrderDto orderDto) {
        return null;
    }

}
