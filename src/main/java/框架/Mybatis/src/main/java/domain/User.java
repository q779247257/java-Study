package domain;

import lombok.Data;

import java.util.Date;

//自动生成set get to string
public class User {
    private int id;//数据库主键
    private String name;//名字
    private String address;//信息
    private Date birthday;//生日时间
    private String sex;//性别

    public User(int id, String name, String address, Date birthday, String sex) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.sex = sex;
    }
    public User() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                '}';
    }
}
