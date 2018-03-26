package com.switchfully.order.example;

import javax.inject.Named;

@Named
public class ExampleRepository {

    public String getHelloWorld() {
        return "Hello World";
    }
}
