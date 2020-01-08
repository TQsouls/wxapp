package com.wxapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wxapp.dao")
public class WxappApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxappApplication.class, args);
    }

}
