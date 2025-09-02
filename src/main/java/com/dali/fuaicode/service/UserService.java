package com.dali.fuaicode.service;

import cn.hutool.http.server.HttpServerRequest;
import com.dali.fuaicode.model.vo.LoginUserVO;
import com.mybatisflex.core.service.IService;
import com.dali.fuaicode.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 用户 服务层。
 *
 * @author dali
 * @since 2024-06-10
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    String getEncryptPassword(String userPassword);

    LoginUserVO getLoginUserVO(User user);

    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    User getLoginUser(HttpServletRequest request);

    boolean userLogout(HttpServletRequest request);
}
