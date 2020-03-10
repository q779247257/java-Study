package com.qf.pojo;

import java.io.Serializable;
import lombok.Data;
import java.util.Date;


/**
 *  ssm_user
 * @author 轩轩 2020-03-06
 */
@Data
public class SsmUser implements Serializable {
    private static final long serialVersionUID = 8729172063635775360L;

    private Integer id;     // 用户id

    private String username;//用户名

    private String password; //密码

    private String userEmail; // 用户邮箱

    private Date createTime; //数据创建时间（注册时间

    private Date updatTime; //最近更新时间

    private Integer userState; //逻辑删除 0有效/默认（未删除） 1无效（已删除）

    private String userCall; //用户称呼

    private String userHeadPortraitUrl; //用户头像

    public SsmUser() {
    }

}

