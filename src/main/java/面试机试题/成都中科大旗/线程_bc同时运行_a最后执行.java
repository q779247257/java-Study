package 面试机试题.成都中科大旗;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: 轩轩
 * @Date: 2021/2/23 22:40
 * @description: 写三个线程abc，要求bc同时运行，a最后执行
 */
public class 线程_bc同时运行_a最后执行 {
    private static AtomicInteger count = new AtomicInteger(2);
    public static void main(String[] args) {
        Runnable a = ()->{
            Thread thread = Thread.currentThread();
            thread.setName("线程a");

            while (true){
                if (count.get() == 0){
                    for (int i = 0; i < 100; i++) System.out.println(thread.getName() + " 执行职务逻辑："+i);
                    break;
                }
            }
        };

        Runnable b = ()->{
            Thread thread = Thread.currentThread();
            thread.setName("线程b");
            for (int i = 0; i < 101; i++) System.out.println(thread.getName() + " 执行职务逻辑："+i);

            //减1
            count.decrementAndGet();
        };

        Runnable c = ()->{
            Thread thread = Thread.currentThread();
            thread.setName("线程c");
            for (int i = 0; i < 101; i++) System.out.println(thread.getName() + " 执行职务逻辑："+i);

            //减1
            count.decrementAndGet();
        };

        new Thread(a).start();
        new Thread(b).start();
        new Thread(c).start();
    }
}


