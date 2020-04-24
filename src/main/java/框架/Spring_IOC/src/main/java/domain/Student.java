package domain;

/**
 * @Author: 轩轩
 * @Date: 2020/4/24 21:08
 * @description: 学生
 */
public class Student {
    private String name;
    private Integer age;
    private String sex;

    public void init(){
        System.out.println("初始化方法调用");
    }
    public void destroy(){
        System.out.println("销毁方法");

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
