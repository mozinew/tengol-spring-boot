package com.tengol.spring.boot.validate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * Account
 *
 * @author dongrui
 * @date 2019/12/9 15:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String id;

    @NotNull(message = "用户名 不能为空")
    @Length(max = 20, message = "用户名 长度不能超过20")
    private String userName;

    @NotNull(message = "密码 不能为空")
    @Pattern(regexp = "[A-Z][a-z][0-9]", message = "密码 只能由大写字母、小写字母和数字构成")
    private String passWord;

    @DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private Date createTime;

    @NotBlank(message = "别名 不能为空")
    private String alias;

    @Max(value = 10, message = "级别 最大值为10")
    @Min(value = 1, message = "级别 最小值为1")
    private Integer level;

    @Range(min = 0, max = 10, message = "vip 值范围错误")
    private Integer vip;
}
