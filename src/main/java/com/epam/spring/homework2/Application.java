package com.epam.spring.homework2;

import com.epam.spring.homework2.config.FullConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FullConfig.class);
        String[] beansName = context.getBeanDefinitionNames();
        System.out.println("===============");
        for(String bean : beansName){
            System.out.println(context.getBean(bean));
        }
        System.out.println("===============");
        context.close();
    }
}
