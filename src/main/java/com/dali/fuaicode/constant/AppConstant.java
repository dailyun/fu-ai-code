package com.dali.fuaicode.constant;

public interface AppConstant {

    /**
     * 精选应用的优先级
     */
    Integer GOOD_APP_PRIORITY = 99;

    /**
     * 默认应用优先级
     */
    Integer DEFAULT_APP_PRIORITY = 0;


    String CODE_OUTPUT_ROOT_DIR = System.getProperty("user.dir") +"tmp/code_output";

    String CODE_DEPLOY_ROOT_DIR = System.getProperty("user.dir") +"tmp/code_deploy";

    String CODE_DEPLOY_HOST = "http://localhost";



}
