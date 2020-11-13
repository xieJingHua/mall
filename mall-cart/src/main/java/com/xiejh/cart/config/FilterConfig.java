package com.xiejh.cart.config;

import com.xiejh.cart.filter.MallFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiejh
 * @Date 2020/11/13 10:21
 **/
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean getExportFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        // 设置过滤器
        registrationBean.setFilter(new MallFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
