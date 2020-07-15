package com.z;

import com.z.plat.core.spring.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@EnableAsync
public class DevelopFrameApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DevelopFrameApplication.class,args);
        SpringContextHolder.setApplicationContext(applicationContext);
    }
}
