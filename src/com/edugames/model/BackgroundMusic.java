package com.edugames.model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;

public class BackgroundMusic extends Thread {
    // Variables
    private String audioFile = "backgroundSound.wav";
    AudioInputStream audioInputStream;

    public BackgroundMusic() {
        createAudioStream();
        start();
    }

    public void createAudioStream() {
        try {
            // Code to create the audioInputStream
        } catch (Exception e) {
            System.out.println("Error creating audioInputStream: " + e);
        }
    }

    public void run() {
        try {
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
        } catch (Exception e) {
            System.out.println("Error creating and playing clip: " + e);
        }
    }
}
