package com.springboot.hyll.config.security;

import com.springboot.hyll.sys.dao.UserRepository;
import com.springboot.hyll.sys.entity.Tree;
import com.springboot.hyll.sys.entity.User;
import com.springboot.hyll.util.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/4 0004.
 */
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(s);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 用户登陆以后更新用户的最迟登陆时间
        user.setLastLoginDate(new Date());
        userRepository.save(user);
        // 自定义错误的文章说明的地址：http://blog.csdn.net/z69183787/article/details/21190639?locationNum=1&fps=1
        if(user.getState().equalsIgnoreCase("0")){
            throw new LockedException("用户账号被冻结，无法登陆请联系管理员！");
        }
        return user;
    }
}
