package com.dali.fuaicode.service;

import com.dali.fuaicode.model.dto.chathistory.ChatHistoryQueryRequest;
import com.dali.fuaicode.model.entity.User;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.dali.fuaicode.model.entity.ChatHistory;

import java.time.LocalDateTime;

/**
 * 对话历史 服务层。
 *
 * @author 86159
 */
public interface ChatHistoryService extends IService<ChatHistory> {

    boolean addChatMessage(Long appId, String message, String messageType, Long userId);

    boolean delateByAppId(Long appId);

    QueryWrapper getQueryWrapper(ChatHistoryQueryRequest chatHistoryQueryRequest);


    Page<ChatHistory> listAppChatHistoryByPage(Long appId, int pageSize,
                                               LocalDateTime lastCreateTime,
                                               User loginUser);
}
