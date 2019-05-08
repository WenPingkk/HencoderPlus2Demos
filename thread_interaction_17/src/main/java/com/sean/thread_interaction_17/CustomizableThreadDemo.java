package com.sean.thread_interaction_17;

/**
 * Author WenPing
 * CreateTime 2019/5/9.
 * Description:模拟looper
 */

/**
 * 1.looper 的looper方法,死循环,循环中进行task的run方法调换用;使用标志位来实现 控制循环的停止;
 * 2.自定义Thread 在run方法中 调用looper的loop方法
 * 3.创建Thread实例对象,start->引用looper对象.setTask-> 运行.->引用isQuit方法->停止
 */
public class CustomizableThreadDemo implements TestDemo{

    private CustomizbleThread thread = new CustomizbleThread();

    @Override
    public void runTest() {
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.looper.setTask(new Runnable() {
            @Override
            public void run() {
                System.out.println("sean wei");
            }
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.looper.isQuit();
    }

    class CustomizbleThread extends Thread {
        Looper looper = new Looper();

        @Override
        public void run() {
            super.run();
            looper.loop();
        }
    }

    class Looper{
        private Runnable task;
        private boolean isQuit;
        synchronized void setTask(Runnable task) {
            this.task = task;
        }

        synchronized void isQuit() {
            isQuit = true;
        }

        public void loop() {
            while (!isQuit) {
                synchronized (this) {
                    if (task != null) {
                        //调用task的run方法.
                        task.run();
                        task = null;
                    }
                }
            }
        }
    }
}

