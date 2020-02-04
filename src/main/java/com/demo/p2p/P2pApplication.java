package com.demo.p2p;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class P2pApplication {

    public static void main(String[] args) {
        SpringApplication.run(P2pApplication.class, args);
    }

}
