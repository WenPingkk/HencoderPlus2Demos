package com.sean.thread_16.thread.synchronize;

import com.sean.thread_16.thread.TestDemo;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author WenPing
 * CreateTime 2019/5/8.
 * Description:
 */
public class Synchronized3Demo implements TestDemo{

    private int x = 0;
    private int y = 0;
    private volatile int a = 0;
    private String name;
    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock mReadLock = lock.readLock();
    private ReentrantReadWriteLock.WriteLock mWriteLock = lock.writeLock();

    /**
     * 如何使用 lock
     * @param newValue
     */
    private void count(int newValue) {
        mWriteLock.lock();
        try{
            x = newValue;
            y = newValue;
        }finally {
            mWriteLock.unlock();
        }
    }

    private void minus(int delta) {
        synchronized (monitor1) {
            x -= delta;
            y -= delta;
        }
    }

    private void setName(String newName) {
        synchronized (monitor2) {
            name = newName;
        }
    }

    private void print() {
        mReadLock.lock();
        try{
            System.out.println("values:x = "+x+",y = "+y);
        }finally {
            mReadLock.unlock();
        }
    }

    @Override
    public void TestDemo() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 1_000_000; i++) {
                    count(i);
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 1_000_000; i++) {
                    count(i);
                }
            }
        }.start();
    }
}
