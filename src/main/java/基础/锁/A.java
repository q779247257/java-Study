package 基础.锁;

/**
 * @Author: 轩轩
 * @Date: 2021/2/19 17:27
 * @description:
 */
public class A {
    private int num = 0;

    public int getNum() {
        return num;
    }


    /*
        对象锁  不同的对象不会竞争锁
        如果 synchronized 关键字加在 static 上 则不同的对象也会竞争锁 锁的就是 synchronized (A.class)
     */
    public synchronized void synIncrease(){

//        synchronized (this){
//
//        }

        num++;
    }


    public  void increase(){
        num++;
    }
}
