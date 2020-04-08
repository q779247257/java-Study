package 网络编程.原型模式;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/7
 * @描述：
 */
public class Client {

    public static void main(String[] args) {
        //new一个对象
        Thing thing = new Thing();
        //拷贝一个对象
        Thing clone = thing.clone();

        System.out.println("结果为："+(thing==clone));
    }
}
