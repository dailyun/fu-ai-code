package com.dali.fuaicodeapp.service;


import com.dali.fuaicodemodel.model.dto.app.AppAddRequest;
import com.dali.fuaicodemodel.model.dto.app.AppQueryRequest;
import com.dali.fuaicodemodel.model.entity.App;
import com.dali.fuaicodemodel.model.entity.User;
import com.dali.fuaicodemodel.model.vo.AppVO;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
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
