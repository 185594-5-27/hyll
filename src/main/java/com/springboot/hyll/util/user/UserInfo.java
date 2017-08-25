package com.springboot.hyll.util.user;

import com.springboot.hyll.main.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by Administrator on 2017/8/7 0007.
 */
public class UserInfo {

    /**
     * 功能描述：获取当前登陆的用户的信息
     * 注释：强转一个null对象不会产生报错
     * @return
     */
    public static User getUser(){
        return (User)Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).orElse(null);
    }

    /**
     * 功能描述：获取当前登陆的用户的权限集合
     * @return
     */
    public static List<GrantedAuthority> getGrantedAuthority(){
        return (List<GrantedAuthority>)Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getAuthorities()).orElse(new ArrayList<>());
    }

    /**
     * 功能描述：判断当前的用户是否包含某一个权限
     * @param authority
     * 注释：
     *      allMatch：Stream 中全部元素符合传入的 predicate，返回 true
     *      anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
     *      noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
     * @return
     */
    public static boolean hasAuthority(String authority){
        return getGrantedAuthority().stream().anyMatch(obj->obj.getAuthority().equalsIgnoreCase(authority));
    }

}
