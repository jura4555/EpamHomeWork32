package com.epam.spring.homework2.beans;

public class BeanB extends DefaultBean{

    public BeanB(String name, int value) {
        super(name, value);
    }

    public void customInitMethod(){
        System.out.println(name +" Inside customInitMethod");
    }

    public void newCustomInitMethod() {
        System.out.println(name + " Inside myInitMethod");
    }

    public void customDestroyMethod(){
        System.out.println(name +" Inside customDestroyMethod");
    }
}
