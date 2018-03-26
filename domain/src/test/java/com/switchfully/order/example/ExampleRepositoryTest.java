package com.switchfully.order.example;

import org.junit.Assert;
import org.junit.Test;

public class ExampleRepositoryTest {

    @Test
    public void getHelloWorld() {
        ExampleRepository exampleRepository = new ExampleRepository();
        String message = exampleRepository.getHelloWorld();
        Assert.assertEquals(message, "Hello World");
    }

}