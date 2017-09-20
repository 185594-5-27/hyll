package com.springboot.hyll.sys.entity;

import com.springboot.hyll.config.common.base.entity.QueryBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/4 0004.
 */
@Entity
public class UserRole extends QueryBase implements Serializable {

    private static final long serialVersionUID = 1471536236334880784L;
    @Id
    @GeneratedValue
    private Long id;
    // 权限代码
    private String name;
    // 权限名称
    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_associate_tree", joinColumns = { @JoinColumn(name ="role_id" )}, inverseJoinColumns = { @JoinColumn(name = "tree_id") })
    private List<Tree> treeList;

    // 菜单树集合
    @Transient
    private String treeArray;

    public String getTreeArray() {
        return treeArray;
    }

    public void setTreeArray(String treeArray) {
        this.treeArray = treeArray;
    }

    public List<Tree> getTreeList() {
        return treeList;
    }

    public void setTreeList(List<Tree> treeList) {
        this.treeList = treeList;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void packagingTrees(String treeArray){
        Tree tree = null;
        List<Tree> trees = new ArrayList<>();
        for(String id:treeArray.split(",")){
            if(!id.isEmpty()){
                tree = new Tree(Long.parseLong(id));
                trees.add(tree);
            }
        }
        this.setTreeList(trees);
    }

}
