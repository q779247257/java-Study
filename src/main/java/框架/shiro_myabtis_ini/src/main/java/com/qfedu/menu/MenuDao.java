package com.qfedu.menu;

import java.util.List;

/**
 * @Author: 轩轩
 * @Date: 2020/4/7 22:15
 * @description:
 */
public interface MenuDao {

    public List<VMenuInfo> findMenu(String name);
}
