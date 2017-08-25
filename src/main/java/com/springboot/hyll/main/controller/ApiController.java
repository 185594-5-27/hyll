package com.springboot.hyll.main.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Administrator on 2017/8/7 0007.
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "234123";
    }

}
