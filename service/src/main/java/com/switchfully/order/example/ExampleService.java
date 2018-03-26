package com.switchfully.order.example;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ExampleService {

    private final ExampleRepository exampleRepository;

    @Inject
    public ExampleService(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    public String getHelloWorldMessage() {
        return exampleRepository.getHelloWorld();
    }
}
