package com.hzz.hzzcloud.拦截器;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/27 15:33
 */
public class MyInterceptor implements HandlerInterceptor  {

    MyLocalThreadbyinterceptor myLocalThreadbyinterceptor=new MyLocalThreadbyinterceptor();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
