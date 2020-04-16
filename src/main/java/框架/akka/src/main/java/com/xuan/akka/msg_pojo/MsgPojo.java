package com.xuan.akka.msg_pojo;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/14
 * @描述： 消息类
 */
public class MsgPojo {
    private Integer id;
    private String value;

    public MsgPojo(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
