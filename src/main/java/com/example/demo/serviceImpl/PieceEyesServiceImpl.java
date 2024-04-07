package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.utils.Constants;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

@Service
public class PieceEyesServiceImpl {

	private Image transparentImg = new Image(Constants.TRANSPARENT_IMAGE);
	private boolean doubleCheck = false;

	public String checkIfEyesDiscovered(Rectangle rectangle, Map<Rectangle, ImageView> map) {
		String pieceId = map.get(rectangle).getId();
		String pieceColor = "white";
		String kingLocation = giveRectangleByImageId("22", map).getId();
		if (pieceId.charAt(pieceId.length() - 1) % 2 == 0) {
			pieceColor = "black";
			kingLocation = giveRectangleByImageId("19", map).getId();
		}
		boolean result = checkIfEyes(kingLocation, pieceColor, map);
		if (doubleCheck)
			return "D";
		if (result)
			return "Y";
		return "N";
	}

	public boolean checkIfEyes(String destinationLocation, String pieceColor, Map<Rectangle, ImageView> map) {
		List<String> piecesList = new ArrayList<>();
		boolean doesEye = false;
		int checkCounter = 0;
		List<String> blackQueenMoves = new ArrayList<>();
		List<String> whiteQueenMoves = new ArrayList<>();

		for (Integer i = 100; i <= Constants.BLACK_QUEEN_ID;) {
			blackQueenMoves.add(i.toString());
			i = i + 2;
		}
		for (Integer j = 99; j <= Constants.WHITE_QUEEN_ID;) {
			whiteQueenMoves.add(j.toString());
			j = j + 2;
		}
		if (!pieceColor.equals("white")) {
			piecesList.addAll(Arrays.asList("6", "10", "14", "18", "22", "26", "30", "34", "4", "8", "12", "16", "20",
					"24", "28", "32"));
			if (!blackQueenMoves.isEmpty())
				piecesList.addAll(blackQueenMoves);
		} else {
			piecesList.addAll(Arrays.asList("3", "7", "11", "15", "19", "23", "27", "31", "5", "9", "13", "17", "21",
					"25", "29", "33"));
			if (!whiteQueenMoves.isEmpty())
				piecesList.addAll(whiteQueenMoves);
		}

		for (String id : piecesList) {
			if (id.equals("3") || id.equals("6") || id.equals("31") || id.equals("34")) {
				if (giveRectangleByImageId(id, map) != null
						&& !giveRectangleByImageId(id, map).getId().equals(destinationLocation)
						&& rookEyes(giveRectangleByImageId(id, map).getId(), destinationLocation, map)) {
					doesEye = true;
					checkCounter++;
					Constants.CHECKING_PIECE = id;
				}
			} else if (id.equals("11") || id.equals("23") || id.equals("14") || id.equals("26")) {
				if (giveRectangleByImageId(id, map) != null
						&& !giveRectangleByImageId(id, map).getId().equals(destinationLocation)
						&& bishopEyes(giveRectangleByImageId(id, map).getId(), destinationLocation, map)) {
					doesEye = true;
					checkCounter++;
					Constants.CHECKING_PIECE = id;
				}
			} else if (id.equals("15") || id.equals("18") || id.equals("99") || id.equals("101") || id.equals("103")
					|| id.equals("105") || id.equals("107") || id.equals("109") || id.equals("111") || id.equals("113")
					|| id.equals("100") || id.equals("102") || id.equals("104") || id.equals("106") || id.equals("108")
					|| id.equals("110") || id.equals("112") || id.equals("114")) {
				if (giveRectangleByImageId(id, map) != null
						&& !giveRectangleByImageId(id, map).getId().equals(destinationLocation)
						&& rookEyes(giveRectangleByImageId(id, map).getId(), destinationLocation, map)) {
					doesEye = true;
					checkCounter++;
					Constants.CHECKING_PIECE = id;
				}
				if (giveRectangleByImageId(id, map) != null
						&& !giveRectangleByImageId(id, map).getId().equals(destinationLocation)
						&& bishopEyes(giveRectangleByImageId(id, map).getId(), destinationLocation, map)) {
					doesEye = true;
					checkCounter++;
					Constants.CHECKING_PIECE = id;
				}
			} else if (id.equals("7") || id.equals("27") || id.equals("10") || id.equals("30")) {
				if (giveRectangleByImageId(id, map) != null
						&& !giveRectangleByImageId(id, map).getId().equals(destinationLocation)
						&& knightEyes(giveRectangleByImageId(id, map).getId(), destinationLocation, map)) {
					doesEye = true;
					checkCounter++;
					Constants.CHECKING_PIECE = id;
				}
			} else if (id.equals("5") || id.equals("9") || id.equals("13") || id.equals("17") || id.equals("21")
					|| id.equals("25") || id.equals("29") || id.equals("33")) {
				if (giveRectangleByImageId(id, map) != null
						&& !giveRectangleByImageId(id, map).getId().equals(destinationLocation)
						&& whitePawnEyes(id, map, destinationLocation)) {
					doesEye = true;
					checkCounter++;
					Constants.CHECKING_PIECE = id;
				}
			} else if (id.equals("4") || id.equals("8") || id.equals("12") || id.equals("16") || id.equals("20")
					|| id.equals("24") || id.equals("28") || id.equals("32")) {
				if (giveRectangleByImageId(id, map) != null
						&& !giveRectangleByImageId(id, map).getId().equals(destinationLocation)
						&& blackPawnEyes(id, map, destinationLocation)) {
					doesEye = true;
					checkCounter++;
					Constants.CHECKING_PIECE = id;
				}
			}
		}
		if (checkCounter > 1)
			doubleCheck = true;
		return doesEye;
	}

	private boolean blackPawnEyes(String id, Map<Rectangle, ImageView> map, String destinationLocation) {
		List<String> possibleMoves = new ArrayList<>();
		String piecePosition = giveRectangleByImageId(id, map).getId();

		char file = piecePosition.charAt(0);
		char rank = piecePosition.charAt(1);
		rank = (char) (rank - 1);
		possibleMoves.add(Character.toString(file + 1) + rank);
		possibleMoves.add(Character.toString(file - 1) + rank);
		return possibleMoves.contains(destinationLocation);
	}

	private boolean whitePawnEyes(String id, Map<Rectangle, ImageView> map, String destinationLocation) {
		List<String> possibleMoves = new ArrayList<>();
		String piecePosition = giveRectangleByImageId(id, map).getId();

		char file = piecePosition.charAt(0);
		char rank = piecePosition.charAt(1);
		rank = (char) (rank + 1);
		possibleMoves.add(Character.toString(file + 1) + rank);
		possibleMoves.add(Character.toString(file - 1) + rank);
		return possibleMoves.contains(destinationLocation);
	}

	private boolean isValidSquare(char file, char rank) {
		return file >= 'a' && file <= 'h' && rank >= '1' && rank <= '8';
	}

	public boolean knightEyes(String source, String destination, Map<Rectangle, ImageView> map) {
		List<String> possibleMoves = new ArrayList<>();

		char file = source.charAt(0);
		char rank = source.charAt(1);

		int[] fileOffsets = { -1, -2, -2, -1, 1, 2, 2, 1 };
		int[] rankOffsets = { -2, -1, 1, 2, 2, 1, -1, -2 };

		for (int i = 0; i < 8; i++) {
			char destFile = (char) (file + fileOffsets[i]);
			char destRank = (char) (rank + rankOffsets[i]);

			if (isValidSquare(destFile, destRank)) {
				possibleMoves.add(Character.toString(destFile) + destRank);
			}
		}
		if (possibleMoves.contains(destination))
			return true;
		return false;
	}

	private boolean rookEyes(String source, String destination, Map<Rectangle, ImageView> map) {
		List<String> squares = new ArrayList<>();
		boolean rookEyes = true;
		boolean rookEyesNotSameRankFile = false;
		char rookCol = source.charAt(0);
		int rookRow = Integer.parseInt(source.substring(1));
		char kingCol = destination.charAt(0);
		int kingRow = Integer.parseInt(destination.substring(1));

		if (rookCol == kingCol) {
			for (int i = rookRow + 1; i < kingRow; i++) {
				if (map.get(giveRectangleById(rookCol + String.valueOf(i), map)).getImage().getPixelReader().getArgb(25,
						30) != transparentImg.getPixelReader().getArgb(25, 30))
					rookEyes = false;
			}
		} else if (rookRow == kingRow) {
			for (char c = (char) (rookCol + 1); c < kingCol; c++) {
				if (map.get(giveRectangleById(c + String.valueOf(rookRow), map)).getImage().getPixelReader().getArgb(25,
						30) != transparentImg.getPixelReader().getArgb(25, 30))
					rookEyes = false;
			}
		} else {
			return rookEyesNotSameRankFile;
		}

		return rookEyes;
	}

	private boolean bishopEyes(String source, String kingLocation, Map<Rectangle, ImageView> map) {

		char sourceFile = source.charAt(0);
		char sourceRank = source.charAt(1);
		char destFile = kingLocation.charAt(0);
		char destRank = kingLocation.charAt(1);
		boolean bishopEyes = true;

		int fileDiff = Math.abs(sourceFile - destFile);
		int rankDiff = Math.abs(sourceRank - destRank);
		List<String> squaresList = new ArrayList<>();

		if (fileDiff == rankDiff) {
			int fileStep = (destFile > sourceFile) ? 1 : -1;
			int rankStep = (destRank > sourceRank) ? 1 : -1;

			char file = (char) (sourceFile + fileStep);
			char rank = (char) (sourceRank + rankStep);
			while (file != destFile) {
				String square = Character.toString(file) + Character.toString(rank);
				if (!(file >= 'a' && file <= 'h' && rank >= '1' && rank <= '8')) {
					return false;
				}
				if (map.get(giveRectangleById(square, map)).getImage().getPixelReader().getArgb(25,
						30) != transparentImg.getPixelReader().getArgb(25, 30))
					bishopEyes = false;

				file = (char) (file + fileStep);
				rank = (char) (rank + rankStep);
			}
		} else
			return false;

		return bishopEyes;
	}

	public Rectangle giveRectangleByImageId(String id, Map<Rectangle, ImageView> map) {
		for (Map.Entry<Rectangle, ImageView> entry : map.entrySet()) {
			ImageView image = entry.getValue();

			if (image.getId().equals(id)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public Rectangle giveRectangleById(String id, Map<Rectangle, ImageView> map) {
		for (Map.Entry<Rectangle, ImageView> entry : map.entrySet()) {
			Rectangle rectangle = entry.getKey();

			if (rectangle.getId().equals(id)) {
				return rectangle;
			}
		}
		return null;
	}

}
