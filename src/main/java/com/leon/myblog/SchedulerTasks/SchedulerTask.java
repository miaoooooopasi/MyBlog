package com.leon.myblog.SchedulerTasks;

import com.leon.myblog.service.ArticleService;
import com.leon.myblog.service.SendMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：leon
 * @date ：Created in 2020-04-10 15:05
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Component
public class SchedulerTask {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    SendMailService sendMailService;
    @Autowired
    ArticleService articleService;

    //@Async("taskScheduler")
    //cron参数 的* * * * * * ：分别对应，秒、分、时、日、月、年
    //@Scheduled(cron = "*/6 * * * * ?")
    public void task1(){
        System.out.println("test1:"+Thread.currentThread());
        //sendMailService.sendSimpleMail("1429169422@qq.com","leon","这是测试邮件");
    }

    //@Async("taskScheduler")
    //cron参数 的* * * * * * ：分别对应，秒、分、时、日、月、年
    //@Scheduled(cron = "*/6 * * * * ?")
    public void task2(){

        System.out.println("test2:"+Thread.currentThread());

    }
}
