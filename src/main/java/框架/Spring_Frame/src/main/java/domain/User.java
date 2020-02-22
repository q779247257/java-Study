package domain;

import java.util.Date;

/**
 * @Author: 轩轩
 * @Date: 2020/2/22 14:27
 * @description:
 */
public class User {
    private String name;//名字
    private String age;//年龄
    private String sex;//性别

    public User(String name, String age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public User(){
        System.out.println("对象被引用了");
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void intMethod(){
        System.out.println("对象初始化的方法"+ new Date().toLocaleString());
    }
    public void  destroyMethod(){
        System.out.println("对象被销毁了");
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
