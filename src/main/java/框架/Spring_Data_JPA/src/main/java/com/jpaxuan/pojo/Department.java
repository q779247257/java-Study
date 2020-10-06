package com.jpaxuan.pojo;

import javax.persistence.*;

/**
 * @Author: 轩轩
 * @Date: 2020/10/6 14:41
 * @description: 部门（一个经理只能管一个部门）
 */



@Entity
@Table(name = "jpa_department")
public class Department {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "dept_name")
    private String deptName;


    /**
     * 使用 @OneToOne 来映射 一对一 关联关系
     * 若需要在当前数据表中添加主键则需要使用 @JoinColum 来进行映射，
     * 注意： 1-1 关联关系 所以需要添加  unique = true 唯一约束
     */
    @JoinColumn(name = "mgr_id",unique = true)
    @OneToOne(fetch = FetchType.LAZY)
    private Manager mgr;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Manager getMgr() {
        return mgr;
    }

    public void setMgr(Manager mgr) {
        this.mgr = mgr;
    }
}
