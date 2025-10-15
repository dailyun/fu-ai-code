package com.dali.fuaicodeuser;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.dali.fuaicodeuser.mapper")
@ComponentScan("com.dali")
@EnableDubbo
public class FuAiCodeUserApplication {
    public  static void main(String[] args) {
        SpringApplication.run(FuAiCodeUserApplication.class, args);
    }
}
