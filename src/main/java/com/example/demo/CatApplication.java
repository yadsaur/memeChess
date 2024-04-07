package com.example.demo;

//import javax.lang.model.util.Elements;

import org.springframework.context.ConfigurableApplicationContext;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;

import com.example.demo.utils.CatConstants;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import javafx.concurrent.Worker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CatApplication extends Application {

	public static ConfigurableApplicationContext applicationContext;

	 public static void main(String[] args) {
	        launch(args);
	    }
	 
	@Override
	public void start(Stage primaryStage) throws Exception { 
		
		
//		String htmlContent = "<html><head></head><body style='background-color: #F2F2F2'><h1 style='color: #FF0000'>Hello, World!</h1></body></html>";
//
//    // Create a JavaFX WebView to render the HTML content
//    WebView webView = new WebView();
//    WebEngine webEngine = webView.getEngine();
//    webEngine.loadContent(htmlContent);
//
//    // Extract the styling information using JavaScript
//    webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
//        if (newValue == Worker.State.SUCCEEDED) {
//            JSObject window = (JSObject) webEngine.executeScript("window");
//            String bodyStyle = (String) webEngine.executeScript("document.body.style.cssText");
//            String h1Style = (String) webEngine.executeScript("document.getElementsByTagName('h1')[0].style.cssText");
//
//            // Create a JavaFX label
//            Label label = new Label("Hello, World!");
//
//            // Apply the extracted styling to the label
//            label.setStyle(bodyStyle + ";" + h1Style);
//
//            // Create a JavaFX layout and add the label and WebView
//            StackPane root = new StackPane();
//            root.getChildren().addAll(label, webView);
//
//            // Create a scene and set it on the stage
//            Scene scene = new Scene(root, 400, 300);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        }
//    });
		
//		 String htmlContent = CatConstants.HTML_ELEMENT_AEON;
//		 String htmlContent = CatConstants.GOOGLE;
		 String htmlContent = CatConstants.GPT;



//		  // Replace this with your own complex HTML code
//	        String htmlContent = "<html><head></head><body style='background-color: #F2F2F2'><h1 style='color: #FF0000'>Hello, World!</h1></body></html>";

	        WebView webView = new WebView();
	        WebEngine webEngine = webView.getEngine();

	        // Enable JavaScript execution
	        webEngine.setJavaScriptEnabled(true);

	        // Load the HTML content into the WebView
	        webEngine.loadContent(htmlContent);

	        // Wait for the page to finish loading
	        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
	            if (newValue == Worker.State.SUCCEEDED) {
	                // Retrieve the document object from the loaded web page
	                JSObject window = (JSObject) webEngine.executeScript("window");

	                // Parse the HTML using JSoup
	                Document document = Jsoup.parse(htmlContent);

	                // Extract and manipulate elements using JSoup and JavaScript
	                Elements elements = document.select("*");
	                for (Element element : elements) {
	                    String tagName = element.tagName();
	                    String text = element.text();
	                    String style = element.attr("style");

	                    // Execute JavaScript code to manipulate the elements
	                    window.call("manipulateElement", tagName, text, style);
	                }
	            }
	        });

	        // Load the JavaScript code into the WebView
	        webEngine.executeScript(getJavaScriptCode());

	        // Create a JavaFX layout to hold the WebView
	        StackPane root = new StackPane(webView);

	        // Create a scene and set it on the stage
	        Scene scene = new Scene(root, 400, 300);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    private String getJavaScriptCode() {
	        // JavaScript code to manipulate the elements
	        return "function manipulateElement(tagName, text, style) {"
	                + "   var elements = document.getElementsByTagName(tagName);"
	                + "   for (var i = 0; i < elements.length; i++) {"
	                + "       var element = elements[i];"
	                + "       element.textContent = text;"
	                + "       element.style = style;"
	                + "   }"
	                + "}";
	    }

}
