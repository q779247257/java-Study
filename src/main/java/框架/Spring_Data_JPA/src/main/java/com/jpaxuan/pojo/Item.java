package com.jpaxuan.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: 轩轩
 * @Date: 2020/10/6 15:49
 * @description: 一个商品（Item） 有 多个分类（Category）
 */
@Entity
@Table(name = "jpa_item")
public class Item {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "item_name")
    private String itemName;

    /**
     *  JoinTable 多对多关系下的中间表设置
     *
     *      @JoinTable(name="中间表名称",joinColumns = {
     *             @JoinColumn(name = "本类的外键",referencedColumnName = "本类与外键对应的主键")
     *     },
     *             inverseJoinColumns = {@JoinColumn(name = "对方类的外键",referencedColumnName = "对方类与外键对应的主键")}
     *     )
     *
     */
    @ManyToMany
    @JoinTable(name = "jpa_itme_category",
            joinColumns = {@JoinColumn(name = "item_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "categorie_id",referencedColumnName = "id")}
    )
    private Set<Category> categories = new HashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
