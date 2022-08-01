package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanA;
import com.epam.spring.homework2.beans.BeanE;
import com.epam.spring.homework2.beans.BeanF;
import org.springframework.context.annotation.*;

@Configuration
@Import(BeanConfig.class)
public class FullConfig {

    @Bean
    public BeanA beanA(){
        System.out.println("Create beanA");
        return new BeanA("beanA", 45);
    }

    @Bean
    public BeanE beanE(){
        System.out.println("Create beanE");
        return new BeanE("beanE", 53);
    }

    @Bean
    @Lazy
    public BeanF beanF(){
        System.out.println("Create beanF");
        return new BeanF("beanF", 89);
    }

}
