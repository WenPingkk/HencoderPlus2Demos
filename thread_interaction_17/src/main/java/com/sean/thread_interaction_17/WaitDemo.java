package com.sean.thread_interaction_17;

/**
 * Author WenPing
 * CreateTime 2019/5/9.
 * Description:
 */
public class WaitDemo implements TestDemo{


    private String shareString;
    synchronized void initString() {
        shareString = "sean wei";
    }

    private synchronized void printString() {
        while (shareString == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("String: " + shareString);
    }

    @Override
    public void runTest() {

        new Thread(){
            @Override
            public void run() {
                super.run();
                //记得使用while 而不是 if
                printString();
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                    yield();//出让自己的时间片,等其他执行完自己再执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                initString();
            }
        }.start();
    }
}
