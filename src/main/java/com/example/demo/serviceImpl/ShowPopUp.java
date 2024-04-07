package com.example.demo.serviceImpl;

import org.springframework.stereotype.Service;

import com.example.demo.utils.Constants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

@Service
public class ShowPopUp {

	public void showPopUpWrongMove() {
		Image image = new Image(getClass().getResourceAsStream(randomMeme()));
		ImageView imageView = new ImageView(image);
		StackPane stackPane = new StackPane(imageView);
		Scene scene = new Scene(stackPane);
		Stage popupStage = new Stage();

		popupStage.setScene(scene);
		popupStage.initModality(Modality.APPLICATION_MODAL);
		popupStage.show();

		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> popupStage.close()));
		timeline.setCycleCount(1);
		timeline.play();
	}
	
	public void showPopUpCheckMate() {
		Image image = new Image(getClass().getResourceAsStream(Constants.MEME_IMAGE_CHECKMATE1));
		ImageView imageView = new ImageView(image);
		StackPane stackPane = new StackPane(imageView);
		Scene scene = new Scene(stackPane);
		Stage popupStage = new Stage();

		popupStage.setScene(scene);
		popupStage.initModality(Modality.APPLICATION_MODAL);
		popupStage.show();

		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> popupStage.close()));
		timeline.setCycleCount(1);
		timeline.play();
	}

	public String randomMeme() {
		Random random = new Random();
		String meme = "";
		int randomNumber = random.nextInt(5) + 1;
		switch (randomNumber) {
		case 1 : meme = Constants.MEME_IMAGE_1;
		break;
		case 2 : meme = Constants.MEME_IMAGE_2;
		break;
		case 4 : meme = Constants.MEME_IMAGE_4;
		break;
		case 5 : meme = Constants.MEME_IMAGE_5;
		break;
		}
		return meme;
	}
}
