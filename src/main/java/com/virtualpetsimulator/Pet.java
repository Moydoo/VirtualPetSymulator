package com.virtualpetsimulator;

public class Pet {
    private String name;
    private int hunger;
    private int happiness;
    private int health;
    private int cleanliness;

    public Pet(String name) {
        this.name = name;
        this.hunger = 50;
        this.happiness = 50;
        this.health = 100;
        this.cleanliness = 100;
    }

    public void feed() {
        hunger = Math.max(0, hunger-20);
        happiness = Math.min(100, happiness+10);
        cleanliness = Math.max(0, cleanliness-10);
    }

    public void play() {
        happiness = Math.min(100, happiness+20);
        hunger = Math.min(100, hunger+10);
        cleanliness = Math.max(0, cleanliness-15);
    }

    public void clean() {
        cleanliness = 100;
        happiness = Math.min(100, happiness+5);
    }

    public void sleep() {
        health = Math.min(100, health+20);
        hunger = Math.min(100, hunger+10);
        happiness = Math.min(100, happiness+5);
    }

    public String getName() { return name; }
    public int getHunger() { return hunger; }
    public int getHappiness () { return happiness; }
    public int getHealth() { return health; }
    public int getCleanliness() { return cleanliness;  }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", hunger=" + hunger +
                ", happiness=" + happiness +
                ", health=" + health +
                ", cleanliness=" + cleanliness +
                '}';
    }
}

