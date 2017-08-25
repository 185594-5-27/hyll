package com.springboot.hyll.config.websocket;

import com.springboot.hyll.util.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 聊天控制器
 */
@Controller
public class GreetingController {
    /**session操作类*/
    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;
    /**消息发送工具*/
    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping(value = "/index")
    public  String index(){
        return "/index";
    }
    @RequestMapping(value = "/message")
    public  String ToMessage(){
        return "/message";
    }
    @RequestMapping(value = "/messaget2")
    public  String ToMessaget2(){
        return "/messaget2";
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




    /**
     * 同样的发送消息   只不过是ws版本  http请求不能访问
     * 根据用户key发送消息
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/msg/hellosingle")
    public void greeting2(InMessage message) throws Exception {
        Map<String,String> params = new HashMap(1);
        params.put("test","test");
        //这里没做校验
        String sessionId=webAgentSessionRegistry.getSessionIds(message.getId()).stream().findFirst().get();
        template.convertAndSendToUser(sessionId,"/topic/greetings",new OutMessage("single send to："+message.getId()+", from:" + message.getName() + "!"),createHeaders(sessionId));
    }
    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

}
