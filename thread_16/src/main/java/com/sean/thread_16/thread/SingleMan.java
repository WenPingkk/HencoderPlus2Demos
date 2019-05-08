package com.sean.thread_16.thread;

/**
 * Author WenPing
 * CreateTime 2019/5/8.
 * Description:
 * 懒汉模式,特点.只有在使用时才会进行对象实例化
 * 缺点 第一次实例化会稍微慢
 * 缺点 即使映实例化 么吃调用实例方法进行不必要的同步操作
 */
public class SingleMan {
    private static SingleMan ourInstance;

    /**
     * 构造函数
     */
    private SingleMan() {

    }

    /**
     * 静态关键词修饰的同步代码块
     */
    static synchronized SingleMan newInstance() {
        if (ourInstance == null) {
            synchronized (SingleMan.class) {
                if (ourInstance == null) {
                    ourInstance = new SingleMan();
                }
            }
        }
        return ourInstance;
    }
}
