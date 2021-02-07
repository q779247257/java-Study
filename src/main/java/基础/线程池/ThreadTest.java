package 基础.线程池;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Author: 轩轩
 * @Date: 2021/2/7 17:14
 * @description: 使用多线程（非线程池的方式）添加 10W 条数据
 */
public class ThreadTest  {
    public static void main(String[] args) throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();

        final Random random = new Random();

        ArrayList<Integer> list = new ArrayList<>();

        /**
         * 每循环一次就会创建一个线程 所以在使用非线程池的情况下会比较慢
         */
        for (int i = 0; i < 100000; i++) {
            Thread thread = new Thread(() -> list.add(random.nextInt()));

            thread.start();
            thread.join();
        }

        System.out.println("时间"+(System.currentTimeMillis() - currentTimeMillis));
        System.out.println("大小"+list.size());
    }

}
