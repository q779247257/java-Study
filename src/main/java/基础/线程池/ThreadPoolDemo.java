package 基础.线程池;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 轩轩
 * @Date: 2021/2/7 18:13
 * @description:
 */
public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        ExecutorService executorService1 = Executors.newCachedThreadPool();//比较快的
        ExecutorService executorService2 = Executors.newFixedThreadPool(10);//比较慢的
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();//最慢的

        long currentTimeMillis = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            executorService1.execute(new MyTask(i+1));

        }
    }


}

class MyTask implements Runnable{

    private int i ;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "--" + i);

//        try {
            //模拟业务逻辑
//            Thread.sleep(1000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public MyTask(int i) {
        this.i = i;
    }
}

