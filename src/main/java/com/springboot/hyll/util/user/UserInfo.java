package com.springboot.hyll.util.user;

import com.springboot.hyll.sys.entity.Tree;
import com.springboot.hyll.sys.entity.User;
import com.springboot.hyll.sys.entity.UserRole;
import com.springboot.hyll.util.node.NodeUtil;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

/**
 * Created by Administrator on 2017/8/7 0007.
 */
public class UserInfo {



    /**
     * 功能描述：加载菜单节点的数据
     * @return
     */
    public static List<Tree> loadUserTree(){
        Map<Long,Tree> treeMap = new HashMap<Long,Tree>();
        User user = getUser();
        for(UserRole userRole:user.getRoles()){
            for(Tree tree:userRole.getTreeList()){
                treeMap.put(tree.getId(),tree);
            }
        }
        return NodeUtil.getChildNodes(new ArrayList<Tree>(treeMap.values()),0l);
    }

    /**
     * 功能描述：实现对密码进行MD5加密
     * @param password
     * @return
     */
    public static String encode(String password){
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(password, "hyll");
    }

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
