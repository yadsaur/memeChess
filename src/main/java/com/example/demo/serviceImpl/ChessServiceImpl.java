package com.example.demo.serviceImpl;

import org.springframework.stereotype.Service;

import com.example.demo.ChessApplication;
import com.example.demo.utils.Constants;
import com.example.demo.utils.Sounds;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

@Service
public class ChessServiceImpl {
	
	public void showChessScreen() {
		try {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(Constants.CHESSBOARD_FXML));
		loader.load();
		Parent root  = loader.getRoot();
		Stage primaryStage = new Stage();
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root));
        new Sounds().playSound(Constants.THEME_MUSIC);
        Image icon = new Image(getClass().getResourceAsStream(Constants.LOGO));
        primaryStage.getIcons().add(icon);
        primaryStage.show();
        primaryStage.setOnCloseRequest(w ->{
        	new ChessApplication().stop();
        });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
