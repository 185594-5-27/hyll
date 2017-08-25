package com.springboot.hyll.main.controller;

import com.springboot.hyll.main.dao.UserRepository;
import com.springboot.hyll.main.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/2 0002.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/modalTest",method = RequestMethod.POST)
    @ResponseBody
    public String modalTest(User user){
        System.out.println("121212"+user.getUserName());
        return "2122";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        User user = new User();
        user.setUserName("福建好运联联");
        Page<User> pageUser = userRepository.findByAuto(user,new PageRequest(0,10));
        System.out.println("--index--"+pageUser.getContent().size()+"---"+userRepository.findByUserName("福建好运联联").size()+"--"+userRepository.findAll().size());
        User userDetails = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        System.out.println("------"+userDetails.getUserName());
        return  "index";
    }


}
