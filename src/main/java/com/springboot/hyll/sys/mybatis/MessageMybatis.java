package com.springboot.hyll.sys.mybatis;

import com.springboot.hyll.sys.entity.Message;

/**
 * Created by Administrator on 2017/9/25 0025.
 */
public interface MessageMybatis {

    Message get(Message message);
}
