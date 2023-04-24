package com.xuecheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : yin
 * @Date: 2023/4/23
 * @Description:
 */
@EnableSwagger2
@SpringBootApplication
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class,args);
    }
}
