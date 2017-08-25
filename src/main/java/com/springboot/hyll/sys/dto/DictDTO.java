package com.springboot.hyll.sys.dto;

import com.springboot.hyll.util.json.JsonHelper;

import java.io.Serializable;

/*
* 类描述：实现数据类型的转换
* @auther linzf
* @create 2017/8/24 0024 
*/
public class DictDTO implements Serializable {

    private static final long serialVersionUID = -1416748306354609078L;

    private Long id;
    // 字典类型
    private String type;
    // 字典编码
    private String code;
    // 字典描述
    private String text;

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

    @Override
    public String toString() {
        return JsonHelper.object2json(this);
    }
}
