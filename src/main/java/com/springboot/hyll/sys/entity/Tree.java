package com.springboot.hyll.sys.entity;

import com.springboot.hyll.config.common.base.entity.QueryBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
* 类描述：
* @auther linzf
* @create 2017/9/14 0014 
*/
@Entity
public class Tree extends QueryBase implements Serializable,Comparable<Tree> {

    private static final long serialVersionUID = 6367070425790693190L;

    public Tree(){
        super();
    }

    public Tree(Long id){
        this.id = id;
    }

    @Id //2
    @GeneratedValue
    private Long id;
    // 父节点ID
    private Long pId;
    // 菜单节点名字
    private String name;
    // 菜单响应地址
    private String url;
    // 菜单编码
    private String code;
    // 菜单样式
    private String icon;
    // 菜单状态（0：禁用；1：启用）
    private String state;
    // 菜单顺序
    private Long treeOrder;
    // 菜单节点是否选中的状态
    @Transient
    private boolean checked;

    // 父菜单信息
    @Transient
    private Tree tree;
    // 子菜单节点信息
    @Transient
    private List<Tree> child = new ArrayList<Tree>();

    public List<Tree> getChild() {
        return child;
    }

    public void setChild(List<Tree> child) {
        this.child = child;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public Long getTreeOrder() {
        return treeOrder;
    }

    public void setTreeOrder(Long treeOrder) {
        this.treeOrder = treeOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * 功能描述：实现集合根据treeOrder字段进行排序的功能
     * @param o
     * @return
     */
    @Override
    public int compareTo(Tree o) {
        long i = this.getTreeOrder() - o.getTreeOrder();
        return Integer.parseInt(i+"");
    }
}
