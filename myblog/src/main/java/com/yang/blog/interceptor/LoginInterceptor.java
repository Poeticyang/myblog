package com.yang.blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 拦截器，拦截未登录的用户
 */
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断session中是否保存了用户，如果没有保存，重定向到登录页面，拦截住
        Object user = request.getSession().getAttribute("user");
        if (user == null){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
