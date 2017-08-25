package com.springboot.hyll.sys.entity;

import com.springboot.hyll.config.common.base.entity.QueryBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/*
* 类描述：数据字典实体类
* @auther linzf
* @create 2017/8/21 0021 
*/
@Entity
public class Dict extends QueryBase implements Serializable {
    // alt + enter 就可实现序列化接口
    private static final long serialVersionUID = 7128807041841801639L;

    // 流水id
    @Id
    @GeneratedValue
    private Long id;
    // 字典类型
    private String type;
    // 字典编码
    private String code;
    // 字典描述
    private String text;
    // 字典值
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
