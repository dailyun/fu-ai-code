package com.dali.fuaicode;


import com.dali.fuaicode.ai.AiCodeGeneratorService;
import com.dali.fuaicode.ai.model.HtmlCodeResult;
import com.dali.fuaicode.ai.model.MultiFileCodeResult;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class aiTest1 {
    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;

    @Test
    void generateHtmlCode() {
        HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode("请生成一个页面,内容为<h1>Hello World</h1>");
        Assertions.assertNotNull(result);
    }

    @Test
    void generateMultiFileCode() {
        MultiFileCodeResult result = aiCodeGeneratorService.generateMultiFileCode("请生成一个页面,内容为<h1>Hello World</h1>");
        Assertions.assertNotNull(result);
    }
}
