package com.switchfully.order.example;

import com.switchfully.order.TestApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestApplication.class})
public class ExampleServiceIntegrationTest {

    @Inject
    private ExampleService exampleService;

    @Test
    public void getHelloWorldMessage() {
        String message = exampleService.getHelloWorldMessage();
        Assert.assertEquals(message, "Hello World");
    }

}