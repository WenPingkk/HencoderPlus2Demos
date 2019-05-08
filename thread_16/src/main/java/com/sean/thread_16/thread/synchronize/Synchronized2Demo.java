package com.sean.thread_16.thread.synchronize;

import com.sean.thread_16.thread.TestDemo;

/**
 * Author WenPing
 * CreateTime 2019/5/8.
 * Description:
 */
public class Synchronized2Demo implements TestDemo {

    private int x = 0;

    private synchronized void count() {
        x++;
    }

    @Override
    public void TestDemo() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 1_000_000; i++) {
                    count();
                }
                System.out.println("final x from 1:" + x);
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 1_000_000; i++) {
                    count();
                }
                System.out.println("final x from 2:"+x);
            }
        }.start();
    }
}
