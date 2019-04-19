package com.rx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@MapperScan({"com.rx.authPlat.*.dao","com.rx.demo.dao"})
//@MapperScan("com.rx.demo.dao")
//@ServletComponentScan(value = "com.rx.config")
//@ComponentScan(basePackages={"com"})
public class Rx4AApplication {

    public static void main(String[] args) {
        SpringApplication.run(Rx4AApplication.class, args);
    }

}

