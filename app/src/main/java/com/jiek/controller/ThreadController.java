package com.jiek.controller;

import android.util.Log;

/**
 * Created by jiek on 10/13/16.
 */

public class ThreadController {
    private static final String TAG = "ThreadController";
    Object lock = new Object();
    int count = 0;

    Thread a = new Thread(new Runnable() {
        @Override
        public void run() {
            while (closeFlag) {
                synchronized (lock) {
                    lock.notifyAll();
                    Log.e(TAG, ++count + " run: A 1");
                    try {
                        lock.wait();
                        Thread.sleep(1);//为日志慢点,但与下边的时间可不一致,  在Android中存在log丢失现象,所以加此后可看到此状态的全部日志
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    });
    Thread b = new Thread(new Runnable() {
        @Override
        public void run() {
            while (closeFlag) {
                synchronized (lock) {
                    lock.notifyAll();
                    Log.e(TAG, ++count + " run: B   2");
                    try {
                        lock.wait();
//                        Thread.sleep(50);//为日志慢点,但与下边的时间可不一致
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    });

    /**
     * 测试线程问题
     */
    public void doThread() {
        closeFlag = true;
        a.start();
        b.start();
    }

    public void stopThread() {
        synchronized (lock) {
            closeFlag = false;
            lock.notifyAll();
        }
    }

    boolean closeFlag = false;
}
