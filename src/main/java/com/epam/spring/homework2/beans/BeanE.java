package com.epam.spring.homework2.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class BeanE extends DefaultBean{
    public BeanE(String name, int value) {
        super(name, value);
    }

    @PreDestroy
    public void myPreDestroyMethod(){
        System.out.println(name +" Inside @PreDestroy");
    }

    @PostConstruct
    public void myPostConstructMethod(){
        System.out.println(name +" Inside @PostConstruct");
    }
}
