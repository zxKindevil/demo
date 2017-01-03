package com.zhangxin.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangxin
 *         Created on 17/1/3.
 */
@Controller
public class AdminController {

    @Bean
    public SystemWebSocketHandler systemWebSocketHandler() {
        return new SystemWebSocketHandler();
    }


    @RequestMapping("/auditing")
    @ResponseBody
    public String auditing(HttpServletRequest request) {
        //无关代码都省略了
        int unReadNewsCount = 0;
        systemWebSocketHandler().sendMessageToUser("userName", new TextMessage(unReadNewsCount + ""));
        return "";
    }
}

