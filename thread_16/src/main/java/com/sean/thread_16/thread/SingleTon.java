package com.sean.thread_16.thread;

/**
 * Author WenPing
 * CreateTime 2019/5/8.
 * Description:
 * DCL 双重锁机制
 * 特点:避免实例化后调用实例方法反复同步问题.性能更好.
 * 特点 多线程不会出现线程不同步的问题
 * 加volatile 关键字 原因,避免实例创建时不会被重排序.
 * https://blog.csdn.net/www1575066083/article/details/80371085
 */
public class SingleTon {
    private static volatile SingleTon sInstance = null;

    private SingleTon() {

    }

    public static SingleTon getsInstance() {
        if (sInstance == null) {
            synchronized (SingleTon.class) {
                if (sInstance == null) {
                    sInstance = new SingleTon();
                }
            }
        }
        return sInstance;
    }
}
