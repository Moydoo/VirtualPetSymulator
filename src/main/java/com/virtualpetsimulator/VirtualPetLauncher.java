package com.virtualpetsimulator;

import javafx.application.Application;

public class VirtualPetLauncher {
    public static void main(String[] args) {
        // Launch the JavaFX application
        System.out.println("It's time to start a fun game! You will get a Buddy that you need to take care of. Do not kill him! >:");
        Application.launch(VirtualPetGUI.class, args);
    }
}