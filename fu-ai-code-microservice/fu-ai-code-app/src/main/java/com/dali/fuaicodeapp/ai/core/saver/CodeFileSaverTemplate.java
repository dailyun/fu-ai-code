package com.dali.fuaicodeapp.ai.core.saver;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.dali.fuaicodeai.ai.model.enums.CodeGenTypeEnum;
import com.dali.fuaicodecommon.constant.AppConstant;
import com.dali.fuaicodecommon.exception.BusinessException;
import com.dali.fuaicodecommon.exception.ErrorCode;


import java.io.File;
import java.nio.charset.StandardCharsets;


public abstract class CodeFileSaverTemplate<T> {

    protected static final String FILE_SAVE_ROOT_DIR = AppConstant.CODE_OUTPUT_ROOT_DIR;

    public final File saveCode(T  result, Long appId) {
        // validate input
        validateInput(result);
        // generate unique dir
        String baseDirPath = buildUniqueDir(appId);
        // save files
        saveFiles(result, baseDirPath);
        // return
        return new File(baseDirPath);
    }


    /**
     * 验证输入参数的有效性
     *
     * @param result 待验证的输入参数，不能为null
     * @throws IllegalArgumentException 当result参数为null时抛出
     */
    protected void validateInput(T result) {
        // 验证输入参数是否为null，如果为null则抛出异常
        if (result == null) {
            throw new IllegalArgumentException("result cannot be null");
        }
    }

    /**
     * 构建一个唯一的目录名称
     *
     * @return 返回一个唯一的目录名称
     */
    protected final String buildUniqueDir(Long appId){
        if (appId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "appId cannot be null");
        }
        String codeType = getCodeType().getValue();
        String uniqueDirName = StrUtil.format("{}_{}", codeType, appId);
        String dirPath = FILE_SAVE_ROOT_DIR + File.separator + uniqueDirName;
        FileUtil.mkdir(dirPath);
        return dirPath;
    }

    /**
     * 将内容写入文件
     *
     * @param dirPath       文件的目录路径
     * @param filename      文件名
     * @param content       文件内容
     */
    protected final void writeToFile(String dirPath, String filename, String content) {
        if (content != null) {
            String filePath = dirPath + File.separator + filename;
            FileUtil.writeString(content, filePath, StandardCharsets.UTF_8);
        }
    }

    /**
     * 获取代码类型
     *
     * @return 返回代码类型
     */
    protected abstract CodeGenTypeEnum getCodeType();

    /**
     * 保存文件
     *
     * @param result       待保存的代码结果
     * @param baseDirPath  保存文件的基础目录路径
     */
    protected abstract void saveFiles(T result, String baseDirPath);

}
