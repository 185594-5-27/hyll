package com.springboot.hyll.sys.dao;

import com.springboot.hyll.sys.entity.User;
import com.springboot.hyll.config.customrepository.CustomRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
public interface UserRepository extends CustomRepository<User, Long> {

    List<User> findByUserName(String userName);

    User findByLogin(String login);

}
