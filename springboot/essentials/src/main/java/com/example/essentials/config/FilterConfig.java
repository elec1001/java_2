package com.example.essentials.config;

import com.example.essentials.filter.LoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<LoggingFilter> filterRegistrationBean() {
        FilterRegistrationBean<LoggingFilter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new LoggingFilter());
        filterRegistrationBean.addUrlPatterns("/api/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}