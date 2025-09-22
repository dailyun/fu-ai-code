package com.dali.fuaicode.service;

import com.dali.fuaicode.model.dto.app.AppAddRequest;
import com.dali.fuaicode.model.dto.app.AppQueryRequest;
import com.dali.fuaicode.model.entity.User;
import com.dali.fuaicode.model.vo.AppVO;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.dali.fuaicode.model.entity.App;
import reactor.core.publisher.Flux;

import java.util.List;


public interface AppService extends IService<App> {

    AppVO getAppVO(App app);

    QueryWrapper getQueryWrapper(AppQueryRequest appQueryRequest);

    List<AppVO> getAppVOList(List<App> appList);

    Flux<String> chatToGenCode(Long appId, String message, User loginUser);

    String deployApp(Long appId, User loginUser);

    Long createApp(AppAddRequest appAddRequest, User loginUser);

    void generateAppScreenshotAsync(Long appId, String appUrl);
}
