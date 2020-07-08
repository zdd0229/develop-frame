package com.z.plat.config.mvc;

import com.z.plat.config.sitemesh.Meshsite3Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
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
     * @param dispatcherServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean bean = new ServletRegistrationBean(dispatcherServlet);
        bean.addUrlMappings("*.htm");
        return bean;
    }
}
