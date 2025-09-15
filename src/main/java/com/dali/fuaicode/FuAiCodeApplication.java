package com.dali.fuaicode;

import dev.langchain4j.community.store.embedding.redis.spring.RedisEmbeddingStoreAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.dali.fuaicode.mapper")
@SpringBootApplication(exclude = {RedisEmbeddingStoreAutoConfiguration.class})

public class FuAiCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuAiCodeApplication.class, args);
	}

}
