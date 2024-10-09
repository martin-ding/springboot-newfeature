package com.local.localdemo;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VirtualThreadTest {
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) {
        // start a new thread for statistics
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
            ThreadInfo[] threadInfo = threadBean.dumpAllThreads(false, false);
            updateMaxThreadNum(threadInfo.length);
        }, 10, 10, TimeUnit.MILLISECONDS);

        long start = System.currentTimeMillis();
        // Virtual Thread Pool
//        ExecutorService executor =  Executors.newVirtualThreadPerTaskExecutor();
        //Platform Thread Pool
         ExecutorService executor =  Executors.newFixedThreadPool(2000);
        for (int i = 0; i < 10000; i++) {
            executor.submit(() -> {
                try {
                    // sleep 0.5 s，to simulate IO processing
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException ignored) {
                }
            });
        }
        executor.close();
        System.out.println("max：" + list.get(0) + " platform thread/os thread");
        System.out.printf("totalMillis：%dms\n", System.currentTimeMillis() - start);


    }
    // update current Platform Thread number
    private static void updateMaxThreadNum(int num) {
        if (list.isEmpty()) {
            list.add(num);
        } else {
            Integer integer = list.get(0);
            if (num > integer) {
                list.add(0, num);
            }
        }
    }
}

/**
// Virtual Thread
max：21 platform thread/os thread
        totalMillis：612ms

// Platform Thread  线程数200
        max：208 platform thread/os thread
        totalMillis：25218ms

// Platform Thread  线程数500
        max：508 platform thread/os thread
        totalMillis：10112ms

// Platform Thread  线程数1000
        max：1008 platform thread/os thread
        totalMillis：5112ms

// Platform Thread  线程数2000
        max：2008 platform thread/os thread
        totalMillis：2726ms

 **/