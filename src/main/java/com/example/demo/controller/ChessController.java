package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.serviceImpl.IsCheckMateServiceImpl;
import com.example.demo.serviceImpl.IsCheckServiceImpl;
import com.example.demo.serviceImpl.MoveConstraintsServiceImpl;
import com.example.demo.serviceImpl.PieceEyesServiceImpl;
import com.example.demo.serviceImpl.ShowPopUp;
import com.example.demo.utils.Constants;
import com.example.demo.utils.RectangleMouseEvent;
import com.example.demo.utils.Sounds;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import net.rgielen.fxweaver.core.FxmlView;

@RestController
@FxmlView(Constants.CHESSBOARD_FXML)
public class ChessController {

	@FXML
	private GridPane chessBoard;

	@FXML
	private AnchorPane mainAnchorPane;

	@FXML
	private ImageView a1i, a2i, a3i, a4i, a5i, a6i, a7i, a8i, b1i, b2i, b3i, b4i, b5i, b6i, b7i, b8i, c1i, c2i, c3i,
			c4i, c5i, c6i, c7i, c8i, d1i, d2i, d3i, d4i, d5i, d6i, d7i, d8i, e1i, e2i, e3i, e4i, e5i, e6i, e7i, e8i,
			f1i, f2i, f3i, f4i, f5i, f6i, f7i, f8i, g1i, g2i, g3i, g4i, g5i, g6i, g7i, g8i, h1i, h2i, h3i, h4i, h5i,
			h6i, h7i, h8i;

	@FXML
	private Rectangle a1, a2, a3, a4, a5, a6, a7, a8, b1, b2, b3, b4, b5, b6, b7, b8, c1, c2, c3, c4, c5, c6, c7, c8,
			d1, d2, d3, d4, d5, d6, d7, d8, e1, e2, e3, e4, e5, e6, e7, e8, f1, f2, f3, f4, f5, f6, f7, f8, g1, g2, g3,
			g4, g5, g6, g7, g8, h1, h2, h3, h4, h5, h6, h7, h8;

	@Autowired
	MoveConstraintsServiceImpl moveConstraintsServiceImpl;

	private static Color ORIGINAL_COLOR = Color.AQUA;
	private Map<Rectangle, ImageView> rectangleImageViewMap = new HashMap<>();
	private Rectangle previousRectangleClicked = null;
	private Image transparentImg = new Image(Constants.TRANSPARENT_IMAGE);
	private Image whiteQueenImage = new Image(Constants.WHITE_QUEEN_IMAGE);
	private Image blackQueenImage = new Image(Constants.BLACK_QUEEN_IMAGE);
	private boolean whiteTurn = true;

	public void initialize() {
		Integer i = 1;
		Integer j = 2;
		try {
			Field[] rectangleFields = getClass().getDeclaredFields();
			for (Field rectangleField : rectangleFields) {
				if (rectangleField.getType() == Rectangle.class
						&& (Rectangle) rectangleField.get(this) != previousRectangleClicked) {
					rectangleField.setAccessible(true);
					String imageViewFieldName = rectangleField.getName() + "i";
					Field imageViewField = getClass().getDeclaredField(imageViewFieldName);
					imageViewField.setAccessible(true);

					Rectangle rectangle = (Rectangle) rectangleField.get(this);
					ImageView imageView = (ImageView) imageViewField.get(this);
					if (rectangleField.getName().endsWith("1") || rectangleField.getName().endsWith("2")) {
						imageView.setId(Integer.toString(i + 2));
						i++;
						i++;
					} else if (rectangleField.getName().endsWith("7") || rectangleField.getName().endsWith("8")) {
						imageView.setId(Integer.toString(j + 2));
						j++;
						j++;
					} else
						imageView.setId(Integer.toString(-1));
					rectangleImageViewMap.put(rectangle, imageView);
				}
			}
		} catch (IllegalAccessException | NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	public void handleSquareClick(MouseEvent event) {
		Rectangle rectangle = (Rectangle) event.getSource();
		RectangleMouseEvent customEvent = new RectangleMouseEvent(rectangle, event);
		handleSquareClickMeth(customEvent);
	}

	public void handleSquareEnter(MouseEvent event) {
		Rectangle rectangle = (Rectangle) event.getSource();
		RectangleMouseEvent customEvent = new RectangleMouseEvent(rectangle, event);
		handleSquareEnterMeth(customEvent);
	}

	public void handleSquareExit(MouseEvent event) {
		Rectangle rectangle = (Rectangle) event.getSource();
		RectangleMouseEvent customEvent = new RectangleMouseEvent(rectangle, event);
		handleSquareExitMeth(customEvent);
	}

	public void handleSquareClickMeth(RectangleMouseEvent rectangleEvent) {
		try {
			Rectangle rectangle = rectangleEvent.getRectangle();
			double originalHeight = rectangle.getHeight();
			double originalWidth = rectangle.getWidth();
			double originalX = rectangle.getLayoutX();
			double originalY = rectangle.getLayoutY();

			boolean promoteSound = false;
			boolean captureSound = false;

			rectangle.setHeight(originalHeight - 5);
			rectangle.setWidth(originalWidth - 5);
			rectangle.setLayoutX(originalX + 2.5);
			rectangle.setLayoutY(originalY + 2.5);

			PauseTransition pause = new PauseTransition(Duration.millis(20));
			pause.setOnFinished(event -> {
				rectangle.setHeight(originalHeight);
				rectangle.setWidth(originalWidth);
				rectangle.setLayoutX(originalX);
				rectangle.setLayoutY(originalY);
			});
			pause.play();

			if (previousRectangleClicked != null) {
				ImageView imageViewSource = rectangleImageViewMap.get(previousRectangleClicked);
				ImageView imageViewDestination = rectangleImageViewMap.get(rectangle);
				if (imageViewSource.getImage().getPixelReader().getArgb(25, 30) == transparentImg.getPixelReader()
						.getArgb(25, 30)) {
					previousRectangleClicked = rectangle;
					return;
				}
				if (previousRectangleClicked == rectangle) {
					previousRectangleClicked = null;
					return;
				}
				if (imageViewDestination.getId() == null || (Integer.parseInt(imageViewSource.getId())
						% 2 == Integer.parseInt(imageViewDestination.getId()) % 2)) {
					if (imageViewSource.getId().charAt(imageViewSource.getId().length() - 1) % 2 == 1 && !whiteTurn
							|| imageViewSource.getId().charAt(imageViewSource.getId().length() - 1) % 2 == 0
									&& whiteTurn) {
						previousRectangleClicked = null;
						return;
					}
					previousRectangleClicked = rectangle;
				}
				if (imageViewSource.getId().charAt(imageViewSource.getId().length() - 1) % 2 == 1 && !whiteTurn
						|| imageViewSource.getId().charAt(imageViewSource.getId().length() - 1) % 2 == 0 && whiteTurn) {
					previousRectangleClicked = rectangle;
					new ShowPopUp().showPopUpWrongMove();
//					new Sounds().playSound(Constants.PROMOTE_SOUND);
					return;
				}
				if ((imageViewDestination.getId() == null || (Integer.parseInt(imageViewSource.getId())
						% 2 != Integer.parseInt(imageViewDestination.getId()) % 2))
						&& new MoveConstraintsServiceImpl().checkMoveConstraints(previousRectangleClicked, rectangle,
								imageViewSource, rectangleImageViewMap)) {
					if (promotion(imageViewSource, rectangle)) {
						String sourceId = imageViewSource.getId();
						imageViewSource.setId("-1");
						imageViewDestination.setId("99");
						List<String> pawnListWhite = new ArrayList<>();
						pawnListWhite.addAll(Arrays.asList("5", "9", "13", "17", "21", "25", "29", "33"));
						List<String> pawnListBlack = new ArrayList<>();
						pawnListBlack.addAll(Arrays.asList("4", "8", "12", "16", "20", "24", "28", "32"));
						if (pawnListWhite.contains(sourceId)) {
							imageViewDestination.setId(Integer.toString(Constants.WHITE_QUEEN_ID + 2));
							Constants.WHITE_QUEEN_ID = Constants.WHITE_QUEEN_ID + 2;
							imageViewDestination.setImage(whiteQueenImage);
						} else {
							imageViewDestination.setId(Integer.toString(Constants.BLACK_QUEEN_ID + 2));
							Constants.BLACK_QUEEN_ID = Constants.BLACK_QUEEN_ID + 2;
							imageViewDestination.setImage(blackQueenImage);
						}
						imageViewSource.setImage(transparentImg);
						promoteSound = true;

					} else {
						if (imageViewDestination.getImage().getPixelReader().getArgb(25, 30) != transparentImg
								.getPixelReader().getArgb(25, 30))
							captureSound = true;

						String sourceId = imageViewSource.getId();
						imageViewSource.setId("-1");
						imageViewDestination.setId(sourceId);

						imageViewDestination.setImage(imageViewSource.getImage());
						imageViewSource.setImage(transparentImg);
					}
					rectangleImageViewMap.remove(previousRectangleClicked);
					rectangleImageViewMap.put(rectangle, imageViewDestination);
					rectangleImageViewMap.put(previousRectangleClicked, imageViewSource);

					if (new IsCheckServiceImpl().checkIfCheck(rectangle, rectangleImageViewMap)) {
						String discovery = new PieceEyesServiceImpl().checkIfEyesDiscovered(rectangle,
								rectangleImageViewMap);
						if (discovery.equals("Y")) {
							if (new IsCheckMateServiceImpl().checkIfCheckMateDiscovered(rectangleImageViewMap)) {
								new Sounds().playSound(Constants.PROMOTE_SOUND);
								new ShowPopUp().showPopUpCheckMate();
								;
							} else
								new Sounds().playSound(Constants.CHECK_SOUND);
						} else if (discovery.equals("D")) {
							if (new IsCheckMateServiceImpl().checkIfCheckMateDouble(rectangleImageViewMap)) {
								new Sounds().playSound(Constants.PROMOTE_SOUND);
								new ShowPopUp().showPopUpCheckMate();
								;
							} else
								new Sounds().playSound(Constants.CHECK_SOUND);
						} else if (new IsCheckMateServiceImpl().checkIfCheckMate(rectangle, rectangleImageViewMap)) {
							new Sounds().playSound(Constants.PROMOTE_SOUND);
							new ShowPopUp().showPopUpCheckMate();
						} else
							new Sounds().playSound(Constants.CHECK_SOUND);
					} else if (promoteSound)
						new Sounds().playSound(Constants.PROMOTE_SOUND);
					else if (captureSound)
						new Sounds().playSound(Constants.CAPTURE_SOUND);
					else
						new Sounds().playSound(Constants.MOVE_SOUND);
					previousRectangleClicked = null;
					whiteTurn = !whiteTurn;
				} else {
					previousRectangleClicked = rectangle;
				}

			} else
//				if (rectangleImageViewMap.get(rectangle).getImage().getPixelReader().getArgb(25,
//					30) != transparentImg.getPixelReader().getArgb(25, 30))
			{
				previousRectangleClicked = rectangle;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean promotion(ImageView imageViewSource, Rectangle rectangle) {
		List<String> pawnList = new ArrayList<>();
		pawnList.addAll(Arrays.asList("5", "9", "13", "17", "21", "25", "29", "33", "4", "8", "12", "16", "20", "24",
				"28", "32"));

		int destinationNum = Character.getNumericValue(rectangle.getId().charAt(1));
		if (pawnList.contains(imageViewSource.getId()) && (destinationNum == 8 || destinationNum == 1))
			return true;
		return false;
	}

	public Rectangle giveImageById(String id) {
		for (Map.Entry<Rectangle, ImageView> entry : rectangleImageViewMap.entrySet()) {
			Rectangle rectangle = entry.getKey();

			if (rectangle.getId().equals(id)) {
				return rectangle;
			}
		}
		return null;
	}

	public void handleSquareEnterMeth(RectangleMouseEvent rectangleEvent) {
		Color currentColor = (Color) rectangleEvent.getRectangle().getFill();
		ORIGINAL_COLOR = currentColor;
		Color highlightedColor = currentColor.deriveColor(0, 1.5, 0.9, 1.1);
		rectangleEvent.getRectangle().setFill(highlightedColor);
	}

	public void handleSquareExitMeth(RectangleMouseEvent rectangleEvent) {
		rectangleEvent.getRectangle().setFill(ORIGINAL_COLOR);
	}
}
