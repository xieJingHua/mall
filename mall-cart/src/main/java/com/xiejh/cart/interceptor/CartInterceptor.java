package com.xiejh.cart.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * @author xiejh
 * @Date 2020/11/12 23:54
 **/
@Component
public class CartInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Thread thread = Thread.currentThread();
        System.out.println("拦截器："+thread.getId());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Thread thread = Thread.currentThread();
        System.out.println("后置拦截器："+thread.getId());
        Cookie cookie = new Cookie("fdsfdsdf","11111");
//        cookie.setDomain("mall.com");
        cookie.setMaxAge(1000*10000);
        response.addCookie(cookie);
        System.out.println("aaaa");
    }
}
