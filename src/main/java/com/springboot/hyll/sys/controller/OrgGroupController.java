package com.springboot.hyll.sys.controller;

import com.springboot.hyll.config.common.base.controller.BaseController;
import com.springboot.hyll.config.common.base.entity.QueryBase;
import com.springboot.hyll.config.common.base.service.BaseService;
import com.springboot.hyll.config.common.constant.SystemStaticConst;
import com.springboot.hyll.sys.entity.OrgGroup;
import com.springboot.hyll.sys.entity.User;
import com.springboot.hyll.sys.service.OrgGroupService;
import com.springboot.hyll.sys.service.UserService;
import com.springboot.hyll.util.json.JsonHelper;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
     * 功能描述：跳转到更新用户的页面
     * @param entity
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/updateUserPage")
    public String updateUserPage(User entity, Model model) throws Exception {
        entity = userService.get(entity);
        entity.setRoleArray(JsonHelper.list2json(entity.getRoles()));
        model.addAttribute("entity",entity);
        return getPageBaseRoot()+"/updateUser";
    }

    /**
     * 跳转到添加用户的页面
     * @throws Exception
     * */
    @RequestMapping(value="/addUserPage")
    public String addUserPage() throws Exception{
        return getPageBaseRoot()+"/addUser";
    }

    @Override
    public Map<String, Object> update(OrgGroup entity) {
        Map<String,Object> result = new HashMap<String, Object>();
        OrgGroup update = new OrgGroup();
        update.setGroupId(entity.getGroupId());
        update = orgGroupService.get(update);
        update.setName(entity.getName());
        update.setGroupCode(entity.getGroupCode());
        update.setNum(entity.getNum());
        entity = orgGroupService.save(update);
        if(entity!=null){
            result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
            result.put(SystemStaticConst.MSG,"修改数据成功！");
            result.put("entity",entity);
        }else{
            result.put(SystemStaticConst.RESULT,SystemStaticConst.FAIL);
            result.put(SystemStaticConst.MSG,"修改数据失败！");
        }
        return result;
    }

    @Override
    public Map<String, Object> save(OrgGroup entity) {
        String max_node = getMaxNode(orgGroupService.getMaxOrgGroup(entity.getOrgGroup().getNode()),entity.getOrgGroup().getNode());
        entity.setParentNode(entity.getOrgGroup().getNode());
        entity.setNode(max_node);
        return super.save(entity);
    }

    @RequestMapping(value="/updateGroupPage")
    public String updateGroupPage(OrgGroup entity,Model model) throws Exception {
        entity = orgGroupService.get(entity);
        entity.setOrgGroup(orgGroupService.findByNode(entity.getParentNode()));
        model.addAttribute("entity",entity);
        return getPageBaseRoot()+UPDATEPAGE;
    }

    @RequestMapping(value="/addGroupPage")
    public String addPage(OrgGroup entity,Model model) throws Exception {
        entity = orgGroupService.get(entity);
        model.addAttribute("entity",entity);
        return getPageBaseRoot()+ADDPAGE;
    }

    /**
     * 功能描述：获取组织架构底下的相应的用户
     * @return
     */
    @RequestMapping(value = "/userList",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> userList(User user){
        Map<String,Object> result = new HashMap<String, Object>();
        Page<User> page = userService.queryGroupUser(user);
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

    private String getMaxNode(String node,String parentNode){
        String max_node = "";
        if(node==null){
            max_node = parentNode + "001";
        }else{
            String n = (Integer.parseInt(node.substring(node.length()-3)) + 1) + "";
            switch(n.length()){
                case 1:
                    max_node = parentNode + "00" + n;
                    break;
                case 2:
                    max_node = parentNode + "0" + n;
                    break;
                case 3:
                    max_node = parentNode + "" + n;
                    break;
            }
        }
        return max_node;
    }


}
