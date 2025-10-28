package com.dali.fuaicodeapp;


import dev.langchain4j.community.store.embedding.redis.spring.RedisEmbeddingStoreAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(
        scanBasePackages = {
                "com.dali.fuaicodeapp",
                "com.dali.fuaicodeai",   // 加上这一行
                "com.dali.fuaicodecommon",
                "com.dali.fuaicodeclient",
        },
        exclude = {RedisEmbeddingStoreAutoConfiguration.class}
)
@MapperScan("com.dali.fuaicodeapp.mapper")
@EnableCaching
public class FuAiCodeAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(FuAiCodeAppApplication.class, args);
    }
}

