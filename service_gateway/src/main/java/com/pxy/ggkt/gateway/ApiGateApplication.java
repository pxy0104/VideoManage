package com.pxy.ggkt.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-03-21 13:46
 **/

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGateApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateApplication.class,args);
    }
}
