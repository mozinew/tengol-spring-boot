package easyexcel.service.impl;

import easyexcel.service.HelloService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * HelloServiceImpl
 *
 * @author dongrui
 * @date 2020/8/3 12:41
 */
@Service
@Scope("prototype")
public class HelloServiceImpl implements HelloService {
    private final int random;

    public HelloServiceImpl(){
        System.out.println("== HelloService - " + System.currentTimeMillis());
        System.out.println(this);
        random = new Random().nextInt();
        System.out.println("== random : " + random);
    }

    @Override
    public String sayHello(String name) {
        return "Hello, " + random + " for  " + name;
    }
}
