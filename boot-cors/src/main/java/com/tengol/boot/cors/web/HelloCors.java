package com.tengol.boot.cors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * HelloCors
 *
 * @author dongrui
 * @date 2020/8/27 13:45
 */
@Controller
public class HelloCors {

    @GetMapping("")
    public String index() {
        return "index";
    }


    /**
     * 简单请求
     */
    @ResponseBody
    @GetMapping("/simple")
    public String sayHello(String name) {
        System.out.println("=== 简单请求的跨域：hello, " + name);

        return "Hello, " + name;
    }

    @ResponseBody
    @PutMapping("/not-simple")
    public String sayHello2(String name) {
        System.out.println("=== 非简单请求的跨域：hello, " + name);

        return "Hello, " + name;
    }
}
