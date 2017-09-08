package com.springboot.hyll.sys.dao;

import com.springboot.hyll.sys.entity.User;
import com.springboot.hyll.config.customrepository.CustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
public interface UserRepository extends CustomRepository<User, Long> {

    List<User> findByUserName(String userName);

    User findByLogin(String login);

    @Query("select u from User u where u.orgGroup.parentNode like :parentNode% and node like :node% ")
    Page<User> queryGroupUser(@Param("parentNode") String parentNode,@Param("node") String node, Pageable page);

}
