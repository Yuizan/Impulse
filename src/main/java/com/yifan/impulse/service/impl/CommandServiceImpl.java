package com.yifan.impulse.service.impl;

import com.yifan.impulse.common.init.InitConfig;
import com.yifan.impulse.config.ImpulseAutoConfiguration;
import com.yifan.impulse.constans.ImpulseConst;
import com.yifan.impulse.service.CommandService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class CommandServiceImpl implements CommandService {

    private ImpulseConst impulseConst;
    private InitConfig initConfig;
    private ImpulseAutoConfiguration impulseAutoConfiguration;

    public CommandServiceImpl(ImpulseConst impulseConst, InitConfig initConfig, ImpulseAutoConfiguration impulseAutoConfiguration){
        this.impulseConst = impulseConst;
        this.initConfig = initConfig;
        this.impulseAutoConfiguration = impulseAutoConfiguration;
    }

    @Override
    public Long getId() {
        if(impulseConst.getImpulses().size() <= initConfig.getMaxSize() * initConfig.getGenerateRatio()
                && impulseConst.getIsFull().get()){
            synchronized (this){
                if(impulseConst.getImpulses().size() <= initConfig.getMaxSize() * initConfig.getGenerateRatio()
                        && impulseConst.getIsFull().get()){
                    impulseConst.getIsFull().set(false);
                    impulseConst.getIsStartingThread().set(true);
                    for(int i = 0, length = impulseConst.getThreads().size(); i < length;i++){
                        Thread thread = impulseConst.getThreads().get(i);
                        LockSupport.unpark(thread);
                    }
                    impulseConst.getIsStartingThread().set(false);
                }
            }
        }
        Long id = null;
        for(int i = 0; i < initConfig.getCasTimes(); i++){
            if(impulseConst.getIsStartingThread().get()){
                Thread.yield();
            }else{
                id = impulseConst.getImpulses().poll();
                if(id != null){
                    break;
                }
            }
        }
        return id;
    }

}
