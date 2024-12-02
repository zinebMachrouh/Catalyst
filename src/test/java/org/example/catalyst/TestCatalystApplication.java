package org.example.catalyst;

import org.springframework.boot.SpringApplication;

public class TestCatalystApplication {

    public static void main(String[] args) {
        SpringApplication.from(CatalystApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
