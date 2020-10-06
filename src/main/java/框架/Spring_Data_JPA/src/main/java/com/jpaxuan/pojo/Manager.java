package com.jpaxuan.pojo;

import javax.persistence.*;

/**
 * @Author: 轩轩
 * @Date: 2020/10/6 14:41
 * @description: 经理 （一个经理只能管一个部门）
 */

@Entity
@Table(name = "jpa_manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "mgr_name")
    /** 经理名称 */
    private String mgrName;
    /**
     * 对于不维护关联关系 ，没有外键的一方使用 @OneToOne 来进行映射，建议设置 mappedBy=true
     */
    /** 经理管的部门 */
    @OneToOne(mappedBy = "mgr",fetch = FetchType.LAZY)
    private Department dept;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMgrName() {
        return mgrName;
    }

    public void setMgrName(String mgrName) {
        this.mgrName = mgrName;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }
}
