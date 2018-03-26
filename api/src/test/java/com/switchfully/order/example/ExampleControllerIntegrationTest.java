package com.switchfully.order.example;

import com.switchfully.order.TestApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static java.lang.String.format;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Inject
    private ExampleController exampleController;

    @Test
    public void helloWorld() {
        String result = new TestRestTemplate()
                .getForObject(format("http://localhost:%s/%s", port, "example"), String.class);

        Assert.assertEquals(result, "Hello World");
    }

}