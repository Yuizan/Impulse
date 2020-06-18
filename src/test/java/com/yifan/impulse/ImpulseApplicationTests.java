package com.yifan.impulse;

import com.yifan.impulse.service.impl.CommandServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class ImpulseApplicationTests {

    @Autowired
    private CommandServiceImpl commandServiceImpl;

    @Test
    void contextLoads() {
        int max = 1000;
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(max);
        for(int i = 0; i < max; i++){
            executor.execute(()->{
                try {
                    countDownLatch.await();
                    System.out.println(commandServiceImpl.getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            countDownLatch.countDown();
        }
    }

}
