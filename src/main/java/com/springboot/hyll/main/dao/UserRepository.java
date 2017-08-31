package com.springboot.hyll.main.dao;

import com.springboot.hyll.main.entity.User;
import com.springboot.hyll.config.customrepository.CustomRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
public interface UserRepository extends CustomRepository<User, Long> {

    List<User> findByUserName(String userName);

    User findByLogin(String login);

}
