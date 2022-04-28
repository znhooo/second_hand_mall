package com.znho.shm.good;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.znho")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.znho")
public class ServiceGoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGoodApplication.class,args);
    }
}
