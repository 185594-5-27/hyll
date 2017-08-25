package com.springboot.hyll.main.service;

import com.springboot.hyll.main.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
public interface UserService {

    public Page<User> findByAuto(User user);

    public User findByLogin(String login);

    public List<User> findByUserName(String userName);

    public void saveUserWithRollBack(User user);

    public void saveUserWithoutRollBack(User user);
}
