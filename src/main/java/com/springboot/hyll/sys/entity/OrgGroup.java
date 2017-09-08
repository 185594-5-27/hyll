package com.springboot.hyll.sys.entity;

import com.springboot.hyll.config.common.base.entity.QueryBase;

import javax.persistence.*;
import java.io.Serializable;

/*
* 类描述：组织架构实体类
* @auther linzf
* @create 2017/8/30 0030 
*/
@Entity
public class OrgGroup extends QueryBase implements Serializable {

    private static final long serialVersionUID = -5497913846442721836L;

    // 流水id
    @Id
    @GeneratedValue
    private Long groupId;
    // 部门名称
    private String name;
    // 部门编码
    private String groupCode;
    // 部门编号
    private String node;
    // 父部门编号
    private String parentNode;
    // 部门编制人数
    private Long num;
    // 部门现有人数
    private Long existingNum;
    // 父部门信息【添加Transient在初始化的时候不会去创建字段】
    @Transient
    private OrgGroup orgGroup;

    public OrgGroup getOrgGroup() {
        return orgGroup;
    }

    public void setOrgGroup(OrgGroup orgGroup) {
        this.orgGroup = orgGroup;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getParentNode() {
        return parentNode;
    }

    public void setParentNode(String parentNode) {
        this.parentNode = parentNode;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getExistingNum() {
        return existingNum;
    }

    public void setExistingNum(Long existingNum) {
        this.existingNum = existingNum;
    }


}
