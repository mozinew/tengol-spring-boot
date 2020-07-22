package com.tengol.boot.hello.web;

import com.alibaba.fastjson.JSON;
import com.tengol.boot.hello.config.HelloProperties;
import com.tengol.boot.hello.dto.Param;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author dongrui
 * @date 2020/3/20 14:06
 */
@AllArgsConstructor
@RestController
@RequestMapping(value = "/hello")
public class HelloController {
    private final HelloProperties helloProperties;

    @GetMapping("/test")
    public String hello(Param param){
        String json = JSON.toJSONString(param);
        System.out.println(json);
        return json;
    }

    @GetMapping("/prop")
    public String prop(){
        String json = JSON.toJSONString(helloProperties);
        System.out.println(json);
        return json;
    }

}
