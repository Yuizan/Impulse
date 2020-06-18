package com.yifan.impulse.service.impl;

import com.yifan.impulse.common.init.InitConfig;
import com.yifan.impulse.config.ImpulseAutoConfiguration;
import com.yifan.impulse.constans.ImpulseConst;
import com.yifan.impulse.service.CommandService;
import com.yifan.impulse.service.MonitorService;
import org.springframework.stereotype.Service;
import sun.jvmstat.monitor.Monitor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class MonitorServiceImpl implements MonitorService {

    private ImpulseConst impulseConst;
    private InitConfig initConfig;
    private ImpulseAutoConfiguration impulseAutoConfiguration;

    public MonitorServiceImpl(ImpulseConst impulseConst, InitConfig initConfig, ImpulseAutoConfiguration impulseAutoConfiguration){
        this.impulseConst = impulseConst;
        this.initConfig = initConfig;
        this.impulseAutoConfiguration = impulseAutoConfiguration;
    }


    @Override
    public Integer exist() {
        return impulseConst.getImpulses().size();
    }

    @Override
    public Integer thread() {
        return impulseConst.getThreads().size();
    }


}
