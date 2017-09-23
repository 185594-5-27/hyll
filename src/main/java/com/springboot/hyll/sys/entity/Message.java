package com.springboot.hyll.sys.entity;


import com.springboot.hyll.config.common.base.entity.QueryBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* 类描述：消息实体类
* @auther linzf
* @create 2017/9/22 0022 
*/
@Entity
@Table(name="message")
public class Message extends QueryBase implements Serializable {

    private static final long serialVersionUID = -1036053422888848811L;
    // 流水id
    @Id
    @GeneratedValue
    private Long id;
    // 消息内容
    private String content;
    // 消息发送时间
    private Date sendDate;
    // 消息标题
    private String title;
    // 消息图片地址
    private String imgUrl;
    // 消息状态（）
    private String state;
    // 删除状态（0：已删除；1：未删除）
    private String isDelete;

    @OneToMany(mappedBy = "user",fetch=FetchType.LAZY)
    private List<MessageAssociateUser> messageAssociateUserList = new ArrayList<MessageAssociateUser>();

    public List<MessageAssociateUser> getMessageAssociateUserList() {
        return messageAssociateUserList;
    }

    public void setMessageAssociateUserList(List<MessageAssociateUser> messageAssociateUserList) {
        this.messageAssociateUserList = messageAssociateUserList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

}
