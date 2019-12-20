package com.tengol.spring.boot.validate.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * ValidationConfig
 *
 * @author dongrui
 * @date 2019/12/9 17:36
 */
@Configuration
public class ValidationConfig {
    /**
     * 设置 快速失败返回模式
     * @return 快速失败返回模式的校验器
     */
    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory();

        return validatorFactory.getValidator();
    }
}
