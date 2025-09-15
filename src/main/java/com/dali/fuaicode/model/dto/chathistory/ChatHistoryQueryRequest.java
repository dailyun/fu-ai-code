package com.dali.fuaicode.model.dto.chathistory;

import com.dali.fuaicode.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
public class ChatHistoryQueryRequest extends PageRequest implements Serializable {

    private Long id;
    private String message;
    private String messageType;
    private Long appId;
    private Long userId;
    private LocalDateTime lastCreateTime;

    private static final long serialVersionUID = 1L;

}
