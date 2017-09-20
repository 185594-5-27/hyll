package com.springboot.hyll.sys.controller;

/*
* 类描述：
* @auther linzf
* @create 2017/8/24 0024 
*/

import com.springboot.hyll.config.common.base.controller.BaseController;
import com.springboot.hyll.config.common.base.service.BaseService;
import com.springboot.hyll.config.common.constant.SystemStaticConst;
import com.springboot.hyll.sys.entity.Tree;
import com.springboot.hyll.sys.entity.UserRole;
import com.springboot.hyll.sys.mapper.TreeMapper;
import com.springboot.hyll.sys.service.TreeService;
import com.springboot.hyll.sys.service.UserRoleService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController<UserRole> {

    @Inject
    private UserRoleService userRoleService;
    @Inject
    private TreeService treeService;
    @Inject
    private TreeMapper treeMapper;

    @Override
    protected BaseService<UserRole> getService() {
        return userRoleService;
    }

    @Override
    public Map<String, Object> update(UserRole entity) {
        entity.packagingTrees(entity.getTreeArray());
        return super.update(entity);
    }

    @Override
    public Map<String, Object> save(UserRole entity) {
        entity.packagingTrees(entity.getTreeArray());
        return super.save(entity);
    }

    /**
     * 功能描述：根据用户的权限去加载角色数据
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/loadRoleTree",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> loadRoleTree(UserRole entity){
        entity = userRoleService.get(entity);
        List<Tree> treeList = treeService.loadAll(null);
        for(Tree tree:entity.getTreeList()){
            treeList.stream().forEach(t->{
                if(t.getId()==tree.getId()){
                    t.setChecked(true);
                }
            });
        }
        Map<String,Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
        result.put("data",treeMapper.treesToTressDTOs(treeList));
        return result;
    }

}
