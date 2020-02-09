package domain;


import lombok.Data;

import java.util.Date;

/**
 * @Author: 轩轩
 * @Date: 2020/2/10 2:34
 * @description: 订单实体类
 */
@Data
public class Orders {
    private Integer id;//订单id
    private Integer userId;//下单用户id
    private String number;//订单号
    private Date createtime;//创建订单时间
    private String note;//备注
}
