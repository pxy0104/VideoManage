package com.pxy.ggkt.vod;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-03-15 16:30
 **/
@SpringBootApplication
@ComponentScan(basePackages = "com.pxy")  //扫描swagger配置类 引入引用
@EnableDiscoveryClient
public class ServiceVodApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class, args);
    }
}
