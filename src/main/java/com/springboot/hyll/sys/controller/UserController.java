package com.springboot.hyll.sys.controller;

import com.springboot.hyll.config.common.base.controller.BaseController;
import com.springboot.hyll.config.common.base.service.BaseService;
import com.springboot.hyll.config.common.constant.SystemStaticConst;
import com.springboot.hyll.sys.entity.User;
import com.springboot.hyll.sys.entity.UserRole;
import com.springboot.hyll.sys.service.UserRoleService;
import com.springboot.hyll.sys.service.UserService;
import com.springboot.hyll.util.json.JsonHelper;
import com.springboot.hyll.util.user.UserInfo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Inject
    private UserRoleService userRoleService;

    @Override
    protected BaseService<User> getService() {
        return userService;
    }

    /**
     * 功能描述：更新用户状态为禁用/启用
     * @param entity
     * @return
     */
    @RequestMapping(value="/userControl")
    @ResponseBody
    public Map<String,Object> userControl(User entity){
        User update = getService().get(new User(entity.getId()));
        update.setState(entity.getState());
        return super.update(update);
    }

    /**
     * 功能描述：跳转到更新用户的页面
     * @param entity
     * @param model
     * @return
     * @throws Exception
     */
    @Override
    public String updatePage(User entity, Model model) throws Exception {
        entity = getService().get(entity);
        entity.setRoleArray(JsonHelper.list2json(entity.getRoles()));
        model.addAttribute("entity",entity);
        return getPageBaseRoot()+UPDATEPAGE;
    }

    /**
     * 功能描述：加载所有的权限数据
     * @return
     */
    @RequestMapping(value = "/loadRoles",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> loadRoles(){
        Map<String,Object> result = new HashMap<String, Object>();
        List<UserRole> userRoleList = userRoleService.loadAll(null);
        result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
        result.put("list",userRoleList);
        return result;
    }

    /**
     * 功能描述：实现更新用户
     * @param entity
     * @return
     */
    @Override
    public Map<String, Object> update(User entity) {
        Map<String,Object> result = new HashMap<String, Object>();
        User update = new User();
        update.setId(entity.getId());
        update = userService.get(update);
        update.setUserName(entity.getUserName());
        update.setProvince(entity.getProvince());
        update.setCity(entity.getCity());
        update.setDistrict(entity.getDistrict());
        update.setStreetAddress(entity.getStreetAddress());
        update.setAddress(entity.getProvince()+entity.getCity()+entity.getDistrict()+entity.getStreetAddress());
        update.setJob(entity.getJob());
        update.setOrgGroup(entity.getOrgGroup());
        update.packagingRoles(entity.getRoleArray(),userRoleService);
        return super.update(update);
    }

    /**
     * 功能描述：实现增加用户数据
     * @param entity
     * @return
     */
    @Override
    public Map<String, Object> save(User entity) {
        entity.setAddress(entity.getProvince()+entity.getCity()+entity.getDistrict()+entity.getStreetAddress());
        entity.setPassword(UserInfo.encode(entity.getPassword()));
        entity.setState(User.STATE_ON);
        entity.packagingRoles(entity.getRoleArray(),userRoleService);
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
