package com.mydemo.orderman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OrdermanApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdermanApplication.class, args);
    }

}
