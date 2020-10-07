package com.jpaxuan.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: 轩轩
 * @Date: 2020/10/6 15:50
 * @description: 一个类别（Category） 有多个 Item（商品）
 */

@Entity
@Table(name = "jpa_category")
public class Category {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "category_name")
    private String categoryName;

    /**
     *  mappedBy 设置目标属性 一般此属性设置在不维护关联关系的一方
     */
    @ManyToMany(mappedBy = "categories")
    private Set<Item> items = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
