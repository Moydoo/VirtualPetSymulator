package com.virtualpetsimulator;

import com.google.gson.Gson;
import java.io.*;

public class GameManager {
    private Pet pet;
    private Gson gson = new Gson();

    public GameManager(Pet pet) {
        this.pet = pet;
    }

    public void saveProgress(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(pet, writer);
        } catch (IOException e) {
            System.out.println("Failed to save progress.");
        }
    }

    public Pet loadProgress(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, Pet.class);
        } catch (IOException e) {
            System.out.println("Failed to load progress.");
            return null;
        }
    }

    public Pet getPet() {
        return pet;
    }
}