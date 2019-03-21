package com.rx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.rx.demo.dao")
//@ComponentScan(basePackages={"com"})
public class Rx4AApplication {

    public static void main(String[] args) {
        SpringApplication.run(Rx4AApplication.class, args);
    }

}

