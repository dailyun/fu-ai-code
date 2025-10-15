package com.dali.fuaicodeapp.core.parser;


import com.dali.fuaicodeai.ai.model.enums.CodeGenTypeEnum;
import com.dali.fuaicodecommon.exception.BusinessException;
import com.dali.fuaicodecommon.exception.ErrorCode;

import static com.dali.fuaicodeai.ai.model.enums.CodeGenTypeEnum.HTML;

public class CodeParserExecutor {

    private static final HtmlCodeParser htmlCodeParser = new HtmlCodeParser();

    private static final MultiFileCodeParser multiFileCodeParser = new MultiFileCodeParser();

    public static Object executeParser(String codeContent, CodeGenTypeEnum codeGenType) {
        return switch (codeGenType) {
            case HTML -> htmlCodeParser.parseCode(codeContent);
            case MULTI_FILE -> multiFileCodeParser.parseCode(codeContent);
            default -> throw new BusinessException(ErrorCode.SYSTEM_ERROR, "不支持的代码生成类型：" + codeGenType);
        };
    }
}
