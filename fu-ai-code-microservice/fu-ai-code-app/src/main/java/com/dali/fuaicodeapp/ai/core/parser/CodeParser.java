package com.dali.fuaicodeapp.ai.core.parser;


/**
 * 代码解析器接口
 * @param <T>
 *
 * @author dali
 */
public interface CodeParser<T> {

    /**
     * 解析代码
     * @param codeContent
     * @return
     */
    T parseCode(String codeContent);
}
