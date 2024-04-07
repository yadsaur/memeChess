package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
		Application.launch(ChessApplication.class, args);
//		Application.launch(CatApplication.class, args);

	}

}
