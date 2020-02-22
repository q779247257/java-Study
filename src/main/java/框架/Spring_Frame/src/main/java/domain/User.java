package domain;

/**
 * @Author: 轩轩
 * @Date: 2020/2/22 14:27
 * @description:
 */
public class User {
    private String name;//名字
    private String age;//年龄
    private String sex;//性别

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
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
