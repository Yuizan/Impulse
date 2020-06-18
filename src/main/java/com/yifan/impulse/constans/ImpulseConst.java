package com.yifan.impulse.constans;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ImpulseConst {

    private List<Thread> threads = new LinkedList<>();
    private Queue<Long> impulses = new LinkedBlockingQueue<>();
    private AtomicBoolean isFull = new AtomicBoolean(false);
    private AtomicBoolean isStartingThread = new AtomicBoolean(false);

    public AtomicBoolean getIsFull() {
        return isFull;
    }

    public AtomicBoolean getIsStartingThread() {
        return isStartingThread;
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public Queue<Long> getImpulses() {
        return impulses;
    }

}
