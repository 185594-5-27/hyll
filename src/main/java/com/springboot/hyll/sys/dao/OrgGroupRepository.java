package com.springboot.hyll.sys.dao;

import com.springboot.hyll.config.customrepository.CustomRepository;
import com.springboot.hyll.sys.entity.OrgGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*
* 类描述：组织架构分组
* @auther linzf
* @create 2017/8/30 0030 
*/
public interface OrgGroupRepository extends CustomRepository<OrgGroup,Long> {

    @Query("select max(o.node) from OrgGroup o where o.parentNode = :parentNode")
    String getMaxOrgGroup(@Param("parentNode") String parentNode);

    /**
     * 功能描述：根据菜单节点来获取其父节点的信息
     * @param node
     * @return
     */
    OrgGroup findByNode(String node);

}
