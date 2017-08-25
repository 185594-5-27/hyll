package com.springboot.hyll.sys.service;

import com.springboot.hyll.config.common.base.service.BaseService;
import com.springboot.hyll.config.customrepository.CustomRepository;
import com.springboot.hyll.main.entity.UserRole;
import com.springboot.hyll.sys.dao.UserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/*
* 类描述：
* @auther linzf
* @create 2017/8/24 0024 
*/
@Service
@Transactional(rollbackFor={IllegalArgumentException.class})
public class UserRoleService extends BaseService<UserRole> {

    @Inject
    private UserRoleRepository userRoleRepository;

    @Override
    protected CustomRepository<UserRole, Long> getRepository() {
        return userRoleRepository;
    }
}
