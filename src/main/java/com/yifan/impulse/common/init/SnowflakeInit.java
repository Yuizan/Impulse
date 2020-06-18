package com.yifan.impulse.common.init;

import com.yifan.impulse.common.util.IPSectionKeyGenerator;
import com.yifan.impulse.common.util.SnowflakeIdWorker;
import org.springframework.stereotype.Component;

@Component
public class SnowflakeInit {

    private SnowflakeIdWorker idWorker;

    SnowflakeInit(){
        long workerId = IPSectionKeyGenerator.initWorkerId();
        long dataCenterId = IPSectionKeyGenerator.initDataCenterId();
        this.idWorker = new SnowflakeIdWorker(workerId, dataCenterId);
    }

    public Long getId() {
        return idWorker.nextId();
    }
}
