package com.z.plat.config.mvc;

import com.z.plat.config.sitemesh.Meshsite3Filter;
import com.z.plat.core.interceptor.AuthorityInterceptor;
import com.z.plat.core.interceptor.CheckLoginInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigure implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean siteMeshFilter() {
        FilterRegistrationBean fitler = new FilterRegistrationBean();
        fitler.setFilter(new Meshsite3Filter());
        return fitler;
    }

    /**
     * 设置匹配.htm后缀的请求
     *
     * @param dispatcherServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean bean = new ServletRegistrationBean(dispatcherServlet);
        bean.addUrlMappings("*.htm");
        return bean;
    }

    @Bean
    public AuthorityInterceptor authorityInterceptor(){
        return new AuthorityInterceptor();
    }

    @Bean
    public CheckLoginInterceptor checkLoginInterceptor(){
        return new CheckLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册AuthorityInterceptor
        InterceptorRegistration authorityRegistration = registry.addInterceptor(authorityInterceptor());
        authorityRegistration.addPathPatterns("/sys/*/*");

        //注册checkLoginRegistration
        InterceptorRegistration checkLoginRegistration = registry.addInterceptor(checkLoginInterceptor());
        checkLoginRegistration.addPathPatterns("/index.htm", "/sys/*/*");
    }
}
