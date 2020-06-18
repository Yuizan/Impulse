package com.yifan.impulse.common.init;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InitConfig {
    @Value("${impulse.maxSize:100}")
    int maxSize;

    @Value("${impulse.maxActiveCore:1}")
    int maxActiveCore;

    @Value("${impulse.generateRatio:0.5}")
    double generateRatio;

    @Value("${impulse.casTimes:10}")
    int casTimes;

    public int getMaxSize() {
        return maxSize;
    }

    public int getCasTimes() {
        return casTimes;
    }

    public int getMaxActiveCore() {
        return maxActiveCore;
    }

    public double getGenerateRatio() {
        return generateRatio;
    }
}
