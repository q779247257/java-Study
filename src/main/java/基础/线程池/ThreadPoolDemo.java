package 基础.线程池;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

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

        /*
            一般使用自定义的线程池
            存放优先级  1、主线程  2、队列 3、非主线程
            执行优先级  1、主线程  2、非主线程  3、队列
         */
        ThreadPoolExecutor executorService4 = new ThreadPoolExecutor(10, 20,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10));

        long currentTimeMillis = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            executorService4.execute(new MyTask(i+1));
        }
    }


}

class MyTask implements Runnable{

    private int i ;

    @Override
    public void run() {
                System.out.println(Thread.currentThread().getName() + "--" + i);

        try {
//            模拟业务逻辑
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MyTask(int i) {
        this.i = i;
    }
}

