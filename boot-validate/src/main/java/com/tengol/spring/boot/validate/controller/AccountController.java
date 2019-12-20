package com.tengol.spring.boot.validate.controller;

import com.tengol.spring.boot.validate.domain.Account;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * AccountController
 *
 * @author dongrui
 * @date 2019/12/9 15:22
 */
@RestController
public class AccountController {
    @PostMapping("/saveAccount")
    public Object saveAccount(@RequestBody @Valid Account account) {
        System.out.println("保存成功");
        return "保存成功";
    }

    @PostMapping("/addPerson")
    public String addPerson(@RequestBody @Valid Person person, BindingResult result) {
        String errorMsg = getErrorString(result);
        System.out.println(person);
        return errorMsg;
    }

    private String getErrorString(BindingResult result) {
        String errorMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            if (!CollectionUtils.isEmpty(allErrors)) {
                for (ObjectError error : allErrors) {
                    errorMsg = error.getDefaultMessage().concat(",");
                    System.out.println("校验失败：" + error.getDefaultMessage());
                }
            }
        }
        if (errorMsg.endsWith(",")) {
            errorMsg = errorMsg.substring(0, errorMsg.lastIndexOf(","));
        }
        return errorMsg;
    }

    @GetMapping("listPerson")
    public String listPerson(@Valid Person person, BindingResult result,
                             @Validated
                             @RequestParam(name = "grade", required = true) int grade) {
        System.out.println(person);
        System.out.println(result.getAllErrors().size());

        String errorString = getErrorString(result);
        return "list person check : " + errorString;
    }
}
