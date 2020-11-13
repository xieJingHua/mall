package com.xiejh.cart.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author xiejh
 * @Date 2020/11/13 10:12
 **/
public class MallFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        Thread thread = Thread.currentThread();
        System.out.println("过滤器="+thread.getId());
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
