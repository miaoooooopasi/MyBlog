package com.leon.myblog.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author ：leon
 * @date ：Created in 2019-11-08 14:56
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Service
public class TestThreadService {
    @Async
    public void executeAsyncTask(int i) {
        System.out.println("Thread" + Thread.currentThread().getName() + " run：" + i);
    }

}
