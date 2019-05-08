package com.sean.thread_16.thread.synchronize;

import com.sean.thread_16.thread.TestDemo;

/**
 * Author WenPing
 * CreateTime 2019/5/8.
 * Description:
 */
public class Synchronized1Demo implements TestDemo{
    private int x = 0;
    private int y = 0;

    private synchronized void count(int newValue) {
        x = newValue;
        y = newValue;
        if (x != y) {
            System.out.println("x:"+x+",y:"+y);
        }
    }

    @Override
    public void TestDemo() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 1_000_000_000; i++) {
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
