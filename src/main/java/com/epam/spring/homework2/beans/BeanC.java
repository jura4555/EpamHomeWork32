package com.epam.spring.homework2.beans;

public class BeanC extends DefaultBean{

    public BeanC(String name, int value) {
        super(name, value);
    }

    public void customInitMethod(){
        System.out.println(name +" Inside customInitMethod");
    }

    public void customDestroyMethod(){
        System.out.println(name +" Inside customDestroyMethod");
    }
}
