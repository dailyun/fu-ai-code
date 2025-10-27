package com.dali.fuaicodescreenshot;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.dali.fuaicodescreenshot", "com.dali.fuaicodecommon"})

@EnableDubbo
public class FuAiCodeScreenshotApplication {
    public static void main(String[] args) {
        SpringApplication.run(FuAiCodeScreenshotApplication.class, args);
    }
}
