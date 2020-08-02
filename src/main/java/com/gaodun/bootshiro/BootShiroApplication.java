package com.gaodun.bootshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gaodun.bootshiro.mapper")
public class BootShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootShiroApplication.class, args);
    }

}
