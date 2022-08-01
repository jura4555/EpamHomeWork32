package com.epam.spring.homework2.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("Inside MyBeanFactoryPostProcessor.postProcessBeanFactory");
        System.out.println("beanB init method name: " +
                configurableListableBeanFactory.getBeanDefinition("beanB").getInitMethodName());
        configurableListableBeanFactory.getBeanDefinition("beanB").setInitMethodName("newCustomInitMethod");
        System.out.println("beanB new init method name: " +
                configurableListableBeanFactory.getBeanDefinition("beanB").getInitMethodName());
    }
}
