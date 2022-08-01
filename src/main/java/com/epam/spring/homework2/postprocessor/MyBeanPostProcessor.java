package com.epam.spring.homework2.postprocessor;

import com.epam.spring.homework2.validator.Valid;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Valid) {
            ((Valid) bean).validate();
        }
        return bean;
    }
}
