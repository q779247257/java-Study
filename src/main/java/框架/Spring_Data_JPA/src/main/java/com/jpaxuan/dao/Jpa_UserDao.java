package com.jpaxuan.dao;

import com.jpaxuan.entity.Jap_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/6
 * @描述：
 */
public interface Jpa_UserDao extends JpaSpecificationExecutor<Jap_User>, JpaRepository<Jap_User,Integer> {
}
