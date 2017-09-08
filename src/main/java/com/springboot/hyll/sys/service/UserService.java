package com.springboot.hyll.sys.service;

import com.springboot.hyll.config.common.base.service.BaseService;
import com.springboot.hyll.config.customrepository.CustomRepository;
import com.springboot.hyll.sys.dao.UserRepository;
import com.springboot.hyll.sys.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

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

    /**
     * 功能描述：获取组织架构底下的用户的数据
     * @param user
     * @return
     */
    public Page<User> queryGroupUser(User user){
        return userRepository.queryGroupUser(user.getOrgGroup().getParentNode(),user.getOrgGroup().getNode(),this.getPageRequest(user));
    }
}
