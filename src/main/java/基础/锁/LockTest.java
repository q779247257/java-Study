package 基础.锁;

/**
 * @Author: 轩轩
 * @Date: 2021/2/19 17:29
 * @description:
 */
public class LockTest {
    public static void main(String[] args) throws InterruptedException {
        A a = new A();

        long start = System.currentTimeMillis();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                a.increase();
            }
        });


        t1.start();

        for (int i=0 ;  i<10000000 ; i++){
            a.increase();
        }
        t1.join();


        long end = System.currentTimeMillis();

        System.out.println(String.format("%sms",end-start));

        System.out.println(a.getNum());
    }
}
