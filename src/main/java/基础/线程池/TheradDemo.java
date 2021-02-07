package 基础.线程池;

import java.util.HashMap;

/**
 * @Author: 轩轩
 * @Date: 2021/2/7 17:02
 * @description:
 */
public class TheradDemo extends Thread {
    private String name;

    public TheradDemo(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name);
    }

    public static void main(String[] args) {
//        new TheradDemo("轩轩").run();
        new TheradDemo("轩轩").start();




    }
}
