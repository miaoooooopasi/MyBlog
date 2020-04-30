package com.leon.myblog.configs;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
/**
 * @author ：leon
 * @date ：Created in 2020-04-28 15:46
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean corsFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(corsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("corsFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }
    @Bean
    public Filter corsFilter() {
        return new CorsFilter();
    }
}
