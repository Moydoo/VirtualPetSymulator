package com.virtualpetsimulator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VirtualPetGUI extends Application {
    private Pet pet;
    private GameManager gameManager;

    @Override
    public void init() {
        // Initialize pet and gameManager here
        this.pet = new Pet("Buddy"); // Default pet
        this.gameManager = new GameManager(pet);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Virtual Pet Simulator");

        // Create UI components
        Label nameLabel = new Label("Pet Name: " + pet.getName());
        Label hungerLabel = new Label("Hunger: " + pet.getHunger());
        Label happinessLabel = new Label("Happiness: " + pet.getHappiness());
        Label healthLabel = new Label("Health: " + pet.getHealth());
        Label cleanlinessLabel = new Label("Cleanliness: " + pet.getCleanliness());

        // Buttons for interactions
        Button feedButton = new Button("Feed Pet");
        Button playButton = new Button("Play with Pet");
        Button cleanButton = new Button("Clean Pet");
        Button sleepButton = new Button("Put Pet to Sleep");
        Button saveButton = new Button("Save Progress");
        Button loadButton = new Button("Load Progress");

        // Set button actions
        feedButton.setOnAction(e -> {
            pet.feed();
            updateLabels(hungerLabel, happinessLabel, healthLabel, cleanlinessLabel);
            checkIfPetDied(primaryStage);
        });

        playButton.setOnAction(e -> {
            pet.play();
            updateLabels(hungerLabel, happinessLabel, healthLabel, cleanlinessLabel);
            checkIfPetDied(primaryStage);
        });

        cleanButton.setOnAction(e -> {
            pet.clean();
            updateLabels(hungerLabel, happinessLabel, healthLabel, cleanlinessLabel);
            checkIfPetDied(primaryStage);
        });

        sleepButton.setOnAction(e -> {
            boolean slept = pet.sleep();
            if (slept) {
                updateLabels(hungerLabel, happinessLabel, healthLabel, cleanlinessLabel);
                checkIfPetDied(primaryStage);
            } else {
                System.out.println("Your pet is too dirty to sleep! Clean it first.");
            }
        });

        saveButton.setOnAction(e -> {
            gameManager.saveProgress("pet_save.json");
            System.out.println("Progress saved!");
        });

        loadButton.setOnAction(e -> {
            Pet loadedPet = gameManager.loadProgress("pet_save.json");
            if (loadedPet != null) {
                pet = loadedPet;
                updateLabels(hungerLabel, happinessLabel, healthLabel, cleanlinessLabel);
                System.out.println("Progress loaded!");
            }
        });

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                nameLabel, hungerLabel, happinessLabel, healthLabel, cleanlinessLabel,
                feedButton, playButton, cleanButton, sleepButton, saveButton, loadButton
        );

        // Set the scene
        Scene scene = new Scene(layout, 300, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to update all labels
    private void updateLabels(Label hungerLabel, Label happinessLabel, Label healthLabel, Label cleanlinessLabel) {
        hungerLabel.setText("Hunger: " + pet.getHunger());
        happinessLabel.setText("Happiness: " + pet.getHappiness());
        healthLabel.setText("Health: " + pet.getHealth());
        cleanlinessLabel.setText("Cleanliness: " + pet.getCleanliness());
    }

    // Method to check if the pet has died
    private void checkIfPetDied(Stage primaryStage) {
        if (!pet.isAlive()) {
            System.out.println("Your pet has died. Game over!");
            Platform.exit(); // Close the JavaFX application
        }
    }
}