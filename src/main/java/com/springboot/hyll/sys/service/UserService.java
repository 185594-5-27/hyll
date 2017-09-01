package com.springboot.hyll.sys.service;

import com.springboot.hyll.config.common.base.service.BaseService;
import com.springboot.hyll.config.customrepository.CustomRepository;
import com.springboot.hyll.sys.dao.UserRepository;
import com.springboot.hyll.sys.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/*
* 类描述：
* @auther linzf
* @create 2017/9/1 0001 
*/
@Service
@Transactional(rollbackFor={IllegalArgumentException.class})
public class UserService extends BaseService<User> {

    @Inject
    private UserRepository userRepository;

    @Override
    protected CustomRepository<User, Long> getRepository() {
        return userRepository;
    }
}
