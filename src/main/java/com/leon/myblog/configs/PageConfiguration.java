package com.leon.myblog.configs;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;

import java.util.Properties;


/**
 * @author ：leon
 * @date ：Created in 2019-11-14 13:10
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class PageConfiguration {
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("dialect", "postgresql");    //配置 数据库的方言
        properties.setProperty("params", "count=countSql");   //
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}

