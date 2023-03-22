package com.pxy.ggkt.activity;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-03-21 19:21
 **/

@EnableFeignClients(basePackages = "com.pxy")
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.pxy")
public class ServiceActivityApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceActivityApplication.class,args);
    }
}
