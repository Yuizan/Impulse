package com.yifan.impulse.config;

import com.yifan.impulse.common.init.InitConfig;
import com.yifan.impulse.common.init.SnowflakeInit;
import com.yifan.impulse.constans.ImpulseConst;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.concurrent.locks.LockSupport;

@Component
public class ImpulseAutoConfiguration implements ApplicationListener<ContextRefreshedEvent> {

    private SnowflakeInit snowflakeInit;
    private ImpulseConst impulseConst;
    private InitConfig initConfig;

    public ImpulseAutoConfiguration(SnowflakeInit snowflakeInit, ImpulseConst impulseConst, InitConfig initConfig){
        this.snowflakeInit = snowflakeInit;
        this.impulseConst = impulseConst;
        this.initConfig = initConfig;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){
        for(int i = 0,length = initConfig.getMaxActiveCore();i < length;i++){
            Thread thread = new Thread(()->{
                while (true){
                    if(impulseConst.getImpulses().size() >= initConfig.getMaxSize()){
                        impulseConst.getIsFull().set(true);
                        LockSupport.park(this);
                    }
                    impulseConst.getImpulses().add(snowflakeInit.getId());
                }
            });
            impulseConst.getThreads().add(thread);
            thread.start();
        }
    }

}
