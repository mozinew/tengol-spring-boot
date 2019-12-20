package com.tengol.spring.boot.validate;

import com.tengol.spring.boot.validate.config.ValidationUtil;
import com.tengol.spring.boot.validate.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * ValidatorTest
 *
 * @author dongrui
 * @date 2019/12/9 15:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidatorTest {
    @Test
    public void test5() throws IOException {
        Account account = new Account();
        account.setAlias("kalakala");
        account.setUserName("wokalakala");
        account.setPassWord("密码");
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(account);
        if(validResult.hasErrors()){
            String errors = validResult.getErrors();
            System.out.println(errors);
        }
    }
}
