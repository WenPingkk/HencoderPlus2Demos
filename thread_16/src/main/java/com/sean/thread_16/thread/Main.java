package com.sean.thread_16.thread;

import android.support.annotation.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

/**
 * Author WenPing
 * CreateTime 2019/5/8.
 * Description:
 */
public class Main {
    public static void main(String[] args) {
        executor();
    }

    /**
     * 线程池操作
     */
    private static void executor() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("");
            }
        };
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(runnable);
    }

    private static void factory() {
        ThreadFactory factory = new ThreadFactory() {
            int count = 0;
            @Override
            public Thread newThread(@NonNull Runnable r) {
                count ++;
                return new Thread(r, "Thread:" + count);
            }
        };
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "start");
            }
        };
        Thread thread = factory.newThread(runnable);
        thread.start();
        Thread thread2 = factory.newThread(runnable);
        thread2.start();
        Thread thread3 = factory.newThread(runnable);
        thread3.start();
    }

    /**
     * calllable 使用
     * 需要 线程池
     */
    private void callable() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Done";
            }
        };
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> future = executor.submit(callable);
        try {
            String result = future.get();
            System.out.println("result:" + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
