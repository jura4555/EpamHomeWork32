package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validator.Valid;

public abstract class DefaultBean implements Valid {

    protected String name;
    protected int value;
    public DefaultBean(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "name=" + name + ", value=" + value;
    }
    @Override
    public void validate() {
        if (name != null && !name.isEmpty() && value > 0) {
            System.out.println(name + " is valid");
        } else {
            System.out.println(name + " isn`t valid");
        }
    }
}
