package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.beans.BeanC;
import com.epam.spring.homework2.beans.BeanD;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:homework2.properties")
public class BeanConfig {

    @Value("${beanB.name}")
    private String nameB;
    @Value("${beanB.value}")
    private int valueB;
    @Value("${beanC.name}")
    private String nameC;
    @Value("${beanC.value}")
    private int valueC;
    @Value("${beanD.name}")
    private String nameD;
    @Value("${beanD.value}")
    private int valueD;

    @DependsOn({"beanD"})
    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    public BeanB beanB(){
        System.out.println("Create beanB");
        return new BeanB(nameB, valueB);
    }

    @DependsOn({"beanB"})
    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    public BeanC beanC(){
        System.out.println("Create beanC");
        return new BeanC(nameC, valueC);
    }

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    public BeanD beanD(){
        System.out.println("Create beanD");
        return new BeanD(nameD, valueD);
    }
}
