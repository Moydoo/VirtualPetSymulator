package com.virtualpetsimulator;

import java.util.Timer;
import java.util.TimerTask;

public class Pet {
    private String name;
    private int hunger;      // 0 = full, 100 = starving
    private int happiness;  // 0 = sad, 100 = very happy
    private int health;      // 0 = dead, 100 = healthy
    private int cleanliness; // 0 = dirty, 100 = clean
    private boolean isAlive = true; // Track if the pet is alive

    public Pet(String name) {
        this.name = name;
        this.hunger = 50;
        this.happiness = 50;
        this.health = 100;
        this.cleanliness = 100;

        // Start time-based decay
        startDecay();
    }

    // Start time-based decay for stats
    private void startDecay() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                decayStats();
            }
        }, 0, 30000); // Decay every 60 seconds (1 minute)
    }

    // Decay stats over time
    private void decayStats() {
        if (!isAlive) return; // Stop decay if the pet is dead

        hunger = Math.min(100, hunger + 5); // Hunger increases
        happiness = Math.max(0, happiness - 5); // Happiness decreases
        cleanliness = Math.max(0, cleanliness - 5); // Cleanliness decreases
        health = Math.max(0, health - 2); // Health decreases

        // Ensure health never exceeds 100
        health = Math.min(100, health);

        // Check if the pet has died
        if (health <= 0) {
            isAlive = false;
            System.out.println("Your pet has died. Game over!");
            System.exit(0); // Exit the game
        }
    }

    // Feed the pet
    public void feed() {
        if (!isAlive) return; // Stop actions if the pet is dead

        hunger = Math.max(0, hunger - 20);
        happiness = Math.min(100, happiness + 10);
        cleanliness = Math.max(0, cleanliness - 10); // Eating makes the pet dirtier
        health = Math.max(0, health - 5); // Feeding costs energy
    }

    // Play with the pet
    public void play() {
        if (!isAlive) return; // Stop actions if the pet is dead

        happiness = Math.min(100, happiness + 20);
        hunger = Math.min(100, hunger + 10);
        cleanliness = Math.max(0, cleanliness - 15); // Playing makes the pet dirtier
        health = Math.max(0, health - 10); // Playing costs energy
    }

    // Clean the pet
    public void clean() {
        if (!isAlive) return; // Stop actions if the pet is dead

        cleanliness = 100; // Cleaning restores cleanliness to 100
        happiness = Math.min(100, happiness + 5); // Cleaning makes the pet happier
        health = Math.max(0, health - 5); // Cleaning costs energy
    }

    // Put the pet to sleep
    public boolean sleep() {
        if (!isAlive) return false; // Stop actions if the pet is dead

        if (cleanliness >= 80) { // Pet can only sleep if clean
            health = Math.min(100, health + 20); // Sleep regenerates health
            hunger = Math.min(100, hunger + 10); // Sleep increases hunger
            happiness = Math.min(100, happiness + 5); // Sleep makes the pet happier
            return true; // Sleep successful
        } else {
            System.out.println("Your pet is too dirty to sleep! Clean it first.");
            return false; // Sleep failed
        }
    }

    // Check if the pet is alive
    public boolean isAlive() {
        return isAlive;
    }

    // Getters and setters
    public String getName() { return name; }
    public int getHunger() { return hunger; }
    public int getHappiness() { return happiness; }
    public int getHealth() { return health; }
    public int getCleanliness() { return cleanliness; }

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