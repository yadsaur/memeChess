package com.example.demo.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Sounds {

	public void playSound(String filePath) {
        try {
        	
        	Media sound = new Media(getClass().getResource(filePath).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
