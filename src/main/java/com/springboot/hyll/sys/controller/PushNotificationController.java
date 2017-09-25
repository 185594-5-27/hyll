package com.springboot.hyll.sys.controller;

import com.springboot.hyll.config.websocket.OutMessage;
import com.springboot.hyll.config.websocket.SocketSessionRegistry;
import com.springboot.hyll.sys.dao.MessageAssociateUserRepository;
import com.springboot.hyll.sys.dao.MessageRepository;
import com.springboot.hyll.sys.entity.Message;
import com.springboot.hyll.sys.entity.MessageAssociateUser;
import com.springboot.hyll.sys.entity.User;
import com.springboot.hyll.sys.mybatis.MessageMybatis;
import com.springboot.hyll.util.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/*
* 类描述：消息推送的后台实现类
* @auther linzf
* @create 2017/9/22 0022 
*/
@Controller
@RequestMapping("/pushNotification")
public class PushNotificationController {

    /**session操作类*/
    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;
    /**消息发送工具*/
    @Autowired
    private SimpMessagingTemplate template;
    // 用户消息对象集合
    @Inject
    private MessageAssociateUserRepository messageAssociateUserRepository;
    @Inject
    private MessageRepository messageRepository;
    @Inject
    private MessageMybatis messageMybatis;

    @RequestMapping(value = "/SendMessage")
    @ResponseBody
    public Map<String,Object> SendMessage(){
        Map<String,Object> result = new HashMap<String, Object>();
        Message msg = new Message();
        msg.setContent("123");
        messageRepository.save(msg);
        Message msgg = new Message();
        msgg.setId(1l);
        MessageAssociateUser mau = new MessageAssociateUser(new User(1),msgg);
        messageAssociateUserRepository.save(mau);
        messageAssociateUserRepository.save(new MessageAssociateUser(new User(2),msg));
        messageAssociateUserRepository.save(new MessageAssociateUser(new User(9),msg));
        return result;
    }

    @RequestMapping(value = "/getUserMessage")
    @ResponseBody
    public Map<String,Object> getUserMessage(){
        Map<String,Object> result = new HashMap<String, Object>();
        Message msg = new Message();
        msg.setId(1l);
        System.out.println(msg);
        msg = messageMybatis.get(msg);
       result.put("msg",msg);
        return result;
    }

    /**
     * 用户广播
     * 发送消息广播  用于内部发送使用
     * @param request
     * @return
     */
    @GetMapping(value = "/msg/sendcommuser")
    public  @ResponseBody
    OutMessage SendToCommUserMessage(HttpServletRequest request){
        System.out.println(UserInfo.getUser().getUserName());
        List<String> keys=webAgentSessionRegistry.getAllSessionIds().entrySet()
                .stream().map(Map.Entry::getKey)
                .collect(Collectors.toList());
        Date date=new Date();
        keys.forEach(x->{
            webAgentSessionRegistry.getSessionIds(x).stream().forEach(y->{
                template.convertAndSendToUser(y,"/topic/greetings",new OutMessage("commmsg：allsend, " + "send  comm" +date.getTime()+ "!"),createHeaders(y));
            });
        });
        return new OutMessage("sendcommuser, " + new Date() + "!");
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

}
