package com.pxy.ggkt.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-03-21 20:03
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.pxy.ggkt.user.mapper")
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class,args);
    }
}
