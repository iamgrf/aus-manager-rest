package com.aus.interceptor;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 默认会校验完所有属性，然后将错误信息一起返回，但很多时候不需要这样，一个校验失败了，其它就不必校验了
 */
@Configuration
public class ValidatorConfig {

    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true).buildValidatorFactory();
        return validatorFactory.getValidator();
    }

}
