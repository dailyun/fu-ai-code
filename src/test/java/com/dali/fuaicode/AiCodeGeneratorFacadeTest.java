package com.dali.fuaicode;

import com.dali.fuaicode.ai.core.AiCodeGeneratorFacade;
import com.dali.fuaicode.ai.model.enums.CodeGenTypeEnum;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class AiCodeGeneratorFacadeTest {

    @Resource
    private AiCodeGeneratorFacade aiCodeGeneratorFacade;

    @Test
    void generateAndSaveCode() {
        File file = aiCodeGeneratorFacade.generateAndSaveCode("请生成一个页面,内容为<h1>Hello World</h1>", CodeGenTypeEnum.MULTI_FILE);
        Assertions.assertNotNull(file);
    }
}
