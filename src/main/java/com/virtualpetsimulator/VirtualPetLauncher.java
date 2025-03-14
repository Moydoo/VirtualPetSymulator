package com.virtualpetsimulator;

import javafx.application.Application;

public class VirtualPetLauncher {
    public static void main(String[] args) {
        // Create the pet and game manager
        Pet pet = new Pet("Buddy");
        GameManager gameManager = new GameManager(pet);

        // Pass the pet and gameManager to the VirtualPetGUI class
        VirtualPetGUI.setPetAndGameManager(pet, gameManager);

        // Launch the JavaFX application
        Application.launch(VirtualPetGUI.class, args);
    }
}