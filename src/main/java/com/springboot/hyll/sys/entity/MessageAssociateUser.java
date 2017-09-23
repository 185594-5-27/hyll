package com.springboot.hyll.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.hyll.config.common.base.entity.QueryBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
* 类描述：
* @auther linzf
* @create 2017/9/23 0023 
*/
@Entity
@Table(name="message_associate_user")
public class MessageAssociateUser extends QueryBase implements Serializable {

    private static final long serialVersionUID = 3548073768860990988L;
    @Id
    @GeneratedValue
    private Long id;
    // 与消息的关联关系
    @JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="message_id",nullable=true)
    private Message message;
    // 与用户的关联关系
    @JsonIgnore
    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",nullable=true)
    private User user;
    // 删除状态（0：已删除；1：未删除）
    private String isDelete;
    // 阅读状态（0：待阅；1：已阅）
    private String state;
    // 查看时间
    private Date readDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getReadDate() {
        return readDate;
    }

    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }
}
