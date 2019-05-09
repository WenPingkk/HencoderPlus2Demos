package com.sean.thread_interaction_17;

/**
 * Author WenPing
 * CreateTime 2019/5/9.
 * Description:
 */
public class Main {
    public static void main(String[] args) {
//        new CustomizableThreadDemo().runTest();
/*        WaitDemo waitDemo = new WaitDemo();
        waitDemo.runTest();
        waitDemo.initString();*/
        threadInteractionDemo();
    }

    private static void threadInteractionDemo() {
        ThreadInteractionDemo demo = new ThreadInteractionDemo();
        demo.runTest();
    }
}
