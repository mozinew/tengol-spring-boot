package com.tengol.boot.hello.util;

import com.alibaba.fastjson.JSON;

/**
 * HelloUtil
 *
 * @author dongrui
 * @date 2020/11/6 15:52
 */
public class HelloSplit {

    public static void main(String[] args) {
        String course = "语文;数学;英语";
        String[] cs = course.split(";");
        System.out.println(JSON.toJSONString(cs));
    }
}
