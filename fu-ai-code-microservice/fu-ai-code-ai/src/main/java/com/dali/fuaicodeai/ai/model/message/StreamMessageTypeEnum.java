package com.dali.fuaicodeai.ai.model.message;


import lombok.Getter;

@Getter
public enum StreamMessageTypeEnum {

    AI_RESPONSE("ai_response","AI响应"),
    TOOL_REQUEST("tool_request","工具请求"),
    TOOL_EXECUTED("tool_executed","工具执行结果");

    private final String value;
    private final String text;

    StreamMessageTypeEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public static StreamMessageTypeEnum getEnumByValue(String value) {
        for (StreamMessageTypeEnum item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }

}
