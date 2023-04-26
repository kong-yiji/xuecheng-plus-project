package com.xuecheng.content.api.api.content;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : yin
 * @Date: 2023/4/23
 * @Description:
 */
@MapperScan("com.xuecheng.content.mapper")
@SpringBootApplication
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class,args);
    }
}
