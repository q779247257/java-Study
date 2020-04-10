package 网络编程.原型模式;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/7
 * @描述：
 */
public class Thing implements Cloneable {
    public Thing(){
        System.out.println("构造器被调用");
    }

    @Override
    protected Thing clone()   {
        Thing thing = null;
        try {
            thing = (Thing) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return thing;
    }
}
