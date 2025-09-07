package com.dali.fuaicode.ai.core;


import com.dali.fuaicode.ai.AiCodeGeneratorService;
import com.dali.fuaicode.ai.model.HtmlCodeResult;
import com.dali.fuaicode.ai.model.MultiFileCodeResult;
import com.dali.fuaicode.ai.model.enums.CodeGenTypeEnum;
import com.dali.fuaicode.exception.BusinessException;
import com.dali.fuaicode.exception.ErrorCode;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AiCodeGeneratorFacade {

    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;

    public File generateAndSaveCode(String userMessage, CodeGenTypeEnum codeGenTypeEnum) {
        if (codeGenTypeEnum == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "未指定代码生成类型");
        }
        return switch (codeGenTypeEnum) {
            case HTML -> generateAndSaveHtmlCode(userMessage);
            case MULTI_FILE -> generateAndSaveMultiFileCode(userMessage);
            default -> {
                String errorMessage = "不支持的代码生成类型" + codeGenTypeEnum.getValue();
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, errorMessage);
            }
        };
    }


    /**
     * 生成并保存 HTML 代码
     *
     * @param userMessage 用户消息
     * @return 生成的 HTML 文件
     */
    private File generateAndSaveHtmlCode(String userMessage) {
        HtmlCodeResult htmlCodeResult = aiCodeGeneratorService.generateHtmlCode(userMessage);
        return CodeFileSaver.saveHtmlCodeResult(htmlCodeResult);
    }

    /**
     * 生成并保存多文件代码
     *
     * @param userMessage 用户消息
     * @return 生成的多文件代码文件
     */

    private File generateAndSaveMultiFileCode(String userMessage) {
        MultiFileCodeResult multiFileCodeResult = aiCodeGeneratorService.generateMultiFileCode(userMessage);
        return CodeFileSaver.saveMultiCodeResult(multiFileCodeResult);
    }
}
