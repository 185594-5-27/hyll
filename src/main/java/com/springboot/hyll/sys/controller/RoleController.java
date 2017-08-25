package com.springboot.hyll.sys.controller;

/*
* 类描述：
* @auther linzf
* @create 2017/8/24 0024 
*/

import com.springboot.hyll.config.common.base.controller.BaseController;
import com.springboot.hyll.config.common.base.service.BaseService;
import com.springboot.hyll.main.entity.UserRole;
import com.springboot.hyll.sys.service.UserRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController<UserRole> {

    @Inject
    private UserRoleService userRoleService;

    @Override
    protected BaseService<UserRole> getService() {
        return userRoleService;
    }

}
