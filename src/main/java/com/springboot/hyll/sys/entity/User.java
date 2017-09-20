package com.springboot.hyll.sys.entity;

import com.springboot.hyll.config.common.base.entity.QueryBase;
import com.springboot.hyll.sys.service.UserRoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
@Entity
public class User extends QueryBase implements UserDetails {

    private static final long serialVersionUID = 3336609444741094787L;
    //  账号状态-禁用
    public static String STATE_OFF = "0";
    // 账号状态-启用
    public static String STATE_ON = "1";

    public User(){
        super();
    }

    public User(Integer id){
        this.id = id;
    }

    @Id //2
    @GeneratedValue //3
    private Integer id;
    // 名字
    private String userName;
    // 账号
    private String login;
    // 密码
    private String password;
    // 省
    private String province;
    // 市
    private String city;
    // 区
    private String district;
    // 街道地址
    private String streetAddress;
    // 地址
    private String address;
    // 职位名称
    private String job;
    // 出生日期
    private Date birthDate;
    // 账号状态（0：禁用；1：启用）
    private String state;

    // 权限集合数据
    @Transient
    private String roleArray;

    @ManyToOne(optional = true)
    @JoinColumn(name="group_id")
    private OrgGroup orgGroup;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_associate_role", joinColumns = { @JoinColumn(name ="user_id" )}, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private List<UserRole> roles;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRoleArray() {
        return roleArray;
    }

    public void setRoleArray(String roleArray) {
        this.roleArray = roleArray;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

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

    /**
     * 功能描述：组装角色数据集合
     * @param roleArray
     */
    public void packagingRoles(String roleArray){
        List<UserRole> roles = new ArrayList<UserRole>();
        if(roleArray!=null){
            UserRole userRole = null;
            for(String roleId:roleArray.split(",")){
                if(!roleId.isEmpty()){
                    userRole = new UserRole();
                    userRole.setId(Long.parseLong(roleId));
                    roles.add(userRole);
                }
            }
        }
        this.setRoles(roles);
    }

}
