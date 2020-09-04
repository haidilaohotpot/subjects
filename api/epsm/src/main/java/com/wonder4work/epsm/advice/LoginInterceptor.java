package com.wonder4work.epsm.advice;

import com.wonder4work.epsm.domain.LoginLog;
import com.wonder4work.epsm.domain.User;
import com.wonder4work.epsm.service.LoginLogService;
import com.wonder4work.epsm.utils.CookieUtils;
import com.wonder4work.epsm.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author xiezengcheng
 * @date 2020-09-03
 */
@Component
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LoginLogService loginLogService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        String user = CookieUtils.getCookieValue(request, "user",true);
        if (StringUtils.isNotBlank(user)) {
            User loginUser = JsonUtils.jsonToPojo(user, User.class);
            if (loginUser != null && loginUser.getUserId() != null) {
                LoginLog loginLog = new LoginLog();
                loginLog.setCreateTime(new Date());
                loginLog.setIp(request.getRemoteAddr());
                loginLog.setLoginUser(loginUser.getUserId());
                loginLog.setUpdateTime(new Date());
                // 记录登录日志
                loginLogService.save(loginLog);
            }
        }

        super.postHandle(request, response, handler, modelAndView);
    }
}
