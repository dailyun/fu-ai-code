package com.dali.fuaicode.aop;

import com.dali.fuaicode.annotation.AuthCheck;
import com.dali.fuaicode.exception.BusinessException;
import com.dali.fuaicode.exception.ErrorCode;
import com.dali.fuaicode.model.entity.User;
import com.dali.fuaicode.model.enums.UserRoleEnum;
import com.dali.fuaicode.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class AuthInterceptor {
    @Resource
    private UserService userService;

    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttribute = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttribute).getRequest();

        // 从请求属性中获取当前登录用户
        User loginUser = userService.getLoginUser(request);
        UserRoleEnum mustUserRoleEnum = UserRoleEnum.getEnumByValue(mustRole);

        if (mustUserRoleEnum == null) {
            return joinPoint.proceed();
        }

        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        if (userRoleEnum == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        if (UserRoleEnum.ADMIN.equals(mustUserRoleEnum) && !UserRoleEnum.ADMIN.equals(userRoleEnum)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return joinPoint.proceed();
    }
}
