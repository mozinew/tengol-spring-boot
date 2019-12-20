package com.tengol.spring.boot.validate.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * Person
 *
 * @author dongrui
 * @date 2019/12/9 17:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @NotEmpty(message = "姓名不能为空")
    private String name;
    @NotBlank(message = "昵称不能为空")
    private String nickName;
    @Digits(integer = 1, fraction = 2, message = "必须是数字")
    @Range(min = 0, max = 150, message = "年龄必须是0~150范围内")
    private Integer age;
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "生日格式不正确")
    private String birthday;
}
