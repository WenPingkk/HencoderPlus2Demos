package com.sean.thread_interaction_17;

/**
 * Author WenPing
 * CreateTime 2019/5/9.
 * Description:
 * interrupt 不立刻打断,不强制打断
 */
public class ThreadInteractionDemo implements TestDemo {
    /**
     * 中断状态初始时为 false；当另一个线程通过调用 Thread.interrupt() 中断一个线程时，
     * 会出现以下两种情况之一。如果那个线程在执行一个低级可中断阻塞方法，例如 Thread.sleep()、
     * Thread.join() 或 Object.wait()，那么它将取消阻塞并抛出 InterruptedException
     */
    @Override
    public void runTest() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 10; i++) {
                    if (Thread.interrupted()) {
                        //线程被打断
                        System.out.println("interrupted");
                        return;
                    }
                    try {
                        //sleep操作后,interrupted 被重置为false
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("msg " +e.getMessage());
                        return;
                    }
                    System.out.println("number:"+i);
                }
            }
        };
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
