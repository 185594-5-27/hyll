package com.springboot.hyll.sys.controller;

import com.springboot.hyll.config.common.base.controller.BaseController;
import com.springboot.hyll.config.common.base.entity.QueryBase;
import com.springboot.hyll.config.common.base.service.BaseService;
import com.springboot.hyll.config.common.constant.SystemStaticConst;
import com.springboot.hyll.sys.entity.OrgGroup;
import com.springboot.hyll.sys.entity.User;
import com.springboot.hyll.sys.service.OrgGroupService;
import com.springboot.hyll.sys.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 类描述：
* @auther linzf
* @create 2017/8/30 0030 
*/
@Controller
@RequestMapping("/group")
public class OrgGroupController extends BaseController<OrgGroup> {

    @Inject
    private OrgGroupService orgGroupService;

    @Inject
    private UserService userService;

    @Override
    protected BaseService<OrgGroup> getService() {
        return orgGroupService;
    }

    /**
     * 功能描述：获取组织架构底下的相应的用户
     * @return
     */
    @RequestMapping(value = "/userList",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> userList(User user){
        Map<String,Object> result = new HashMap<String, Object>();
        Page<User> page = userService.findByAuto(user);
        result.put("totalCount",page.getTotalElements());
        result.put("result",page.getContent());
        return result;
    }

    /**
     * 功能描述：获取组织架构的整颗菜单树
     * @return
     */
    @RequestMapping(value = "/loadGroupTree",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> loadGroupTree(){
        Map<String,Object> result = new HashMap<String, Object>();
        List<OrgGroup> orgGroupList = orgGroupService.loadAll(null);
        result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG,"加载组织机构数据成功！");
        result.put("data",orgGroupList);
        return result;
    }



}
