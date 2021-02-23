package 面试机试题.成都中科大旗;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Author: 轩轩
 * @Date: 2021/2/23 21:59
 * @description:
 */
public class list排序 {

    /** 简单排序小到大 */
    @Test
    public void sort001() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(9);
        list.add(90);
        list.add(-1);
        list.add(102);
        Collections.sort(list);
        System.out.println("简单排序："+list.toString());
    }

    /** 复杂排序(比较的对象实现 Comparable 接口重写 compareTo 方法) */
    @Test
    public void sort002(){
        ArrayList<User001> list = new ArrayList<>();
        list.add(new User001(18,"轩轩"));
        list.add(new User001(90,"狗熊"));
        list.add(new User001(86,"潘er"));
        list.add(new User001(16,"万林"));
        list.add(new User001(20,"郭香蕉"));
        Collections.sort(list);
        System.out.println("年龄升序排序："+list.toString());
    }


    /** 匿名内部类实现排序 */
    @Test
    public void sort003(){
        ArrayList<User001> list = new ArrayList<>();
        list.add(new User001(18,"轩轩"));
        list.add(new User001(90,"狗熊"));
        list.add(new User001(86,"潘er"));
        list.add(new User001(16,"万林"));
        list.add(new User001(20,"郭香蕉"));
        Collections.sort(list, new Comparator<User001>() {
            @Override
            public int compare(User001 o1, User001 o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println("年龄升序排序："+list.toString());

    }
}


@Data
@AllArgsConstructor
class User001 implements Comparable<User001>{

    /** 年龄 */
    private int age;

    /** 名称 */
    private String name;


    /** 重写 compareTo 方法  一下为年龄升序 */
    @Override
    public int compareTo(User001 o) {
        return this.age - o.age;
    }
}
