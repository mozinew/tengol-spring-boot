package com.tengol.spring.boot.websocket.web;

import com.tengol.spring.boot.websocket.server.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * MessageController
 *
 * @author dongrui
 * @date 2020/7/16 15:13
 */
@Slf4j
@RestController
@RequestMapping(value = "/message")
public class MessageController {

    @GetMapping(value = "/index")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("请求成功");
    }

    @GetMapping(value = "/page")
    public ModelAndView page(){
        return new ModelAndView("websocket");
    }

    @GetMapping(value = "/push/{userId}")
    public ResponseEntity<String> pushMessage(String message, @PathVariable("userId") String userId){
        try {
            WebSocketServer.sendInfo(message, userId);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("服务器向指定用户推送消息失败：{}", e.getMessage(), e);
            return ResponseEntity.ok("push fail " + e.getMessage());
        }
        return ResponseEntity.ok("push successfully!");
    }
}
