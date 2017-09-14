package com.springboot.hyll.sys.entity;

import com.springboot.hyll.config.common.base.entity.QueryBase;

import javax.persistence.*;
import java.io.Serializable;

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
}
