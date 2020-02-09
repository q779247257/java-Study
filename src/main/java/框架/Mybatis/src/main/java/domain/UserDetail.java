package domain;


import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: 轩轩
 * @Date: 2020/2/3 14:58
 * @description:
 */
@Data
public class UserDetail {
    private int id;
    private String nickName;
    private String sex;
    private String address;
    private Date birthday;
    private int userId;//关联UserLogin的id
    private UserLogin userLogin;//嵌套对象
    private List<Orders> ordersList;//嵌套集合
}
