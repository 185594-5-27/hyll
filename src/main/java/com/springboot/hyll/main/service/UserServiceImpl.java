package com.springboot.hyll.main.service;

import com.springboot.hyll.sys.dao.UserRepository;
import com.springboot.hyll.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Caching(

    )
    public Page<User> findByAuto(User user){
        return userRepository.findByAuto(user,new PageRequest(0,10));
    }

    @Cacheable(value = "user", key = "#login")
    public User findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    @Cacheable(value = "user", key = "#userName")
    public List<User> findByUserName(String userName){
       return userRepository.findByUserName(userName);
    }

    @Transactional(rollbackFor={IllegalArgumentException.class})
    public void saveUserWithRollBack(User user){
        userRepository.save(user);
        throw new IllegalArgumentException("测试数据将回滚"); //3
    }

    @Transactional(noRollbackFor={IllegalArgumentException.class})
    public void saveUserWithoutRollBack(User user){
        userRepository.save(user);
        throw new IllegalArgumentException("测试数据将不回滚"); //3
    }

}
