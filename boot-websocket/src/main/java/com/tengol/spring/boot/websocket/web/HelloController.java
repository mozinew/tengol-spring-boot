package com.tengol.spring.boot.websocket.web;

import com.tengol.spring.boot.websocket.common.RestResult;
import com.tengol.spring.boot.websocket.websocket.HelloWebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * HelloController
 *
 * @author dongrui
 * @date 2019/12/20 10:27
 */
@Slf4j
@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String hello(Throwable error) {
        return "/index";
    }

    @GetMapping("/socket/{cid}")
    public String socket(Model model, @PathVariable("cid") String cid) {
        log.info("=== cid : {}", cid);
        model.addAttribute("cid", cid);
        return "/socket";
    }

    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public ResponseEntity pushToWeb(@PathVariable String cid, String message) {
        log.info("=== cid : {}, message : {}", cid, message);
        //发送消息
        try {
            HelloWebSocketServer.sendInfo(message, cid);
            return ResponseEntity.ok(RestResult.success(cid));
        } catch (IOException e) {
            log.error("向客户端{}发送消息失败：{}", cid, e.getMessage(), e);
            return ResponseEntity.ok(RestResult.fail(e.getMessage()));
        }
    }
}
