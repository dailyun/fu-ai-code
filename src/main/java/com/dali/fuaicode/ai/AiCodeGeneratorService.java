package com.dali.fuaicode.ai;

import com.dali.fuaicode.ai.model.HtmlCodeResult;
import com.dali.fuaicode.ai.model.MultiFileCodeResult;
import dev.langchain4j.service.SystemMessage;


public interface AiCodeGeneratorService {

    /**
     * 生成HTML代码
     *
     * @param userMessage 用户输入的消息
     * @return 生成的HTML代码
     */
    @SystemMessage(fromResource = "prompt/codegen-html-system-prompt.txt")
    HtmlCodeResult generateHtmlCode(String userMessage);


    @SystemMessage(fromResource = "prompt/codegen-multi-file-system-prompt.txt")
    MultiFileCodeResult generateMultiFileCode(String userMessage);
}
