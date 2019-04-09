package com.rx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan({"com.rx.shiro.dao","com.rx.demo.dao"})
//@ComponentScan(basePackages={"com.rx"})
public class Rx4AApplication {
    public static void main(String[] args) {
        SpringApplication.run(Rx4AApplication.class, args);
    }

}

