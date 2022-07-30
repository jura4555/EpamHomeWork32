package com.epam.spring.homework1.pet;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Pet {

    private List<Animal> animals;

    public Pet(List<Animal> animals) {
        this.animals = animals;
    }

    public void printPets(){
        for (Animal animal: animals) {
            System.out.println(animal.getClass().getSimpleName());
        }
    }
}
