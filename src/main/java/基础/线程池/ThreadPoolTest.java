package 基础.线程池;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 轩轩
 * @Date: 2021/2/7 18:02
 * @description: 使用线程池的方式往list中添加 10w 条数据 线程池的速度要远远大于非线程池的效率高
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Random random = new Random();

        ArrayList<Integer> list = new ArrayList<>();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 100000; i++) {
            executorService.execute(() -> {
                list.add(random.nextInt());
            });

        }

        //关闭线程池
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("时间"+(System.currentTimeMillis() - currentTimeMillis));
        System.out.println("大小"+list.size());

    }
}
