package com.springboot.hyll.main.controller;

import com.springboot.hyll.config.common.base.entity.QueryBase;
import com.springboot.hyll.config.common.constant.SystemStaticConst;
import com.springboot.hyll.sys.entity.User;
import com.springboot.hyll.main.service.UserService;
import com.springboot.hyll.sys.entity.OrgGroup;
import com.springboot.hyll.util.redis.RedisCache;
import com.springboot.hyll.util.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
@RestController
@RequestMapping("/login")
public class UserController {

       @Autowired
       private UserService userService;

       @Autowired
       private RedisCache redisCache;

    @RequestMapping(value = "/loadData",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
       public Map<String,Object> loadData(QueryBase query){
        System.out.println("查询进来了---"+query.getOrder());
          Map<String,Object> result = new HashMap<String, Object>();
          List<User> userList = new ArrayList<User>();
          for (int i=0;i<100;i++){
              userList.add(UserInfo.getUser());
          }
          result.put("totalCount",100);
          result.put("result",userList);
          return result;
       }

       @RequestMapping(value = "/sendCode",
               method = RequestMethod.GET,
               produces = MediaType.APPLICATION_JSON_VALUE)
       @PreAuthorize("hasAuthority('ROLE_ADMIN')")
       public void sendCode(){
           redisCache.setString("XX","YY");
           System.out.println(redisCache.getString("XX")+"---"+redisCache.getString("XXS"));
           User user = new User();
           user.setUserName("福建好运联联");
           user.setId(1);
           redisCache.setObject("obj",user);
           User user1 = (User)redisCache.getObject("obj");
           System.out.println(user1.getUserName());
       }

    @PreAuthorize("hasAuthority('ROLE_USER')")
       @RequestMapping(value = "/getUser",
               method = RequestMethod.GET,
               produces = MediaType.APPLICATION_JSON_VALUE)
       public Map<String,Object> getUser(){
           User user = UserInfo.getUser();
           Map<String, Object> returnMap = new HashMap<String, Object>();
           returnMap.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
           returnMap.put(SystemStaticConst.MSG,"支付成功！");
           returnMap.put("user",user);
           return returnMap;
       }

       @PreAuthorize("hasAuthority('DRIVER')")
       @RequestMapping(value = "/queryUser/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
       public void queryUser(@PathVariable("id") Long id){
           System.out.println(id+"--------"+userService.findByLogin("hyll")+"---"+userService.findByUserName("linzf").size());
       }

       @RequestMapping(value = "/saveUser",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
       public User saveUser(User user){
           userService.saveUserWithRollBack(user);
           System.out.print(user.getUserName()+"--------");
           return user;
       }

    @RequestMapping(value = "/saveUser2",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public User saveUser2(User user){
        System.out.print(user.getUserName()+"----2222----");
        return user;
    }

    @RequestMapping(value = "/getGroupUserList",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<User> getGroupUserList(){
        OrgGroup og = new OrgGroup();
        og.setGroupId(2l);
        User user = new User();
        user.setOrgGroup(og);
        Page<User> page = userService.findByAuto(user);
        return page;
    }
}
