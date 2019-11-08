package com.leon.myblog.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ：leon
 * @date ：Created in 2019-11-08 15:01
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@SpringBootTest
class TestThreadServiceTest {

    @Autowired
    private TestThreadService testThreadService;

    @Test
    void executeAsyncTask() {
        System.out.println("1");
        for(int i=0;i<10;i++)
        {
            testThreadService.executeAsyncTask(i);
        }
    }
}