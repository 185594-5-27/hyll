package com.springboot.hyll.sys.entity;

import com.springboot.hyll.config.common.base.entity.QueryBase;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
@Entity
public class User extends QueryBase implements UserDetails {

    private static final long serialVersionUID = 3336609444741094787L;

    @Id //2
    @GeneratedValue //3
    private Integer id;
    // 名字
    private String userName;
    // 账号
    private String login;
    // 密码
    private String password;
    // 年龄
    private Integer age;
    // 地址
    private String address;
    // 职位名称
    private String job;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH }, optional = true)
    @JoinColumn(name="group_id")
    private OrgGroup orgGroup;

    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(name = "user_associate_role", joinColumns = { @JoinColumn(name ="user_id" )}, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private List<UserRole> roles;

    public OrgGroup getOrgGroup() {
        return orgGroup;
    }

    public void setOrgGroup(OrgGroup orgGroup) {
        this.orgGroup = orgGroup;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    // 用户账号和密码正确以后才会访问
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        List<UserRole> roles=this.getRoles();
        for(UserRole role:roles){
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}