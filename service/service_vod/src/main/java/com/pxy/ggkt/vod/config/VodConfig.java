package com.pxy.ggkt.vod.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-03-15 16:33
 **/
@Configuration
@MapperScan("com.pxy.ggkt.vod.mapper")
public class VodConfig {
    //when you want to use page plugin,you should inject this plugin.
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
