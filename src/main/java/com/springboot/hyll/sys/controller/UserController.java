package com.springboot.hyll.sys.controller;

import com.springboot.hyll.config.common.base.controller.BaseController;
import com.springboot.hyll.config.common.base.service.BaseService;
import com.springboot.hyll.sys.entity.User;
import com.springboot.hyll.sys.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.Map;

/*
* 类描述：用户维护controller
* @auther linzf
* @create 2017/9/7 0007 
*/
@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {

    @Inject
    private UserService userService;

    @Override
    protected BaseService<User> getService() {
        return userService;
    }

    /**
     * 功能描述：实现增加车辆数据
     * @param entity
     * @return
     */
    @Override
    public Map<String, Object> save(User entity) {
        entity.setAddress(entity.getProvince()+entity.getCity()+entity.getDistrict()+entity.getStreetAddress());
        return super.save(entity);
    }

    /**
     * 功能描述：跳转到选择组织架构页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/pickGroup")
    public String pickGroup() throws Exception{
        return getPageBaseRoot() + "/group";
    }
}
