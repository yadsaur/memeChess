package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.utils.Constants;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

@Service
public class IsCheckServiceImpl {

	private Image transparentImg = new Image(Constants.TRANSPARENT_IMAGE);

	public void isCheck(Rectangle rectangle, Map<Rectangle, ImageView> map) {

	}

	public boolean checkIfCheck(Rectangle rectangle, Map<Rectangle, ImageView> map) {
		String id = map.get(rectangle).getId();
		if (id.equals("3") || id.equals("6") || id.equals("31") || id.equals("34"))
			return rookCheck(rectangle, map);
		else if (id.equals("5") || id.equals("9") || id.equals("13") || id.equals("17") || id.equals("21")
				|| id.equals("25") || id.equals("29") || id.equals("33"))
			return pawnWhiteCheck(rectangle, map);
		else if (id.equals("4") || id.equals("8") || id.equals("12") || id.equals("16") || id.equals("20")
				|| id.equals("24") || id.equals("28") || id.equals("32"))
			return pawnBlackCheck(rectangle, map);
		else if (id.equals("11") || id.equals("23") || id.equals("14") || id.equals("26"))
			return bishopCheck(rectangle, map);
		else if (id.equals("15") || id.equals("18") || id.equals("99") || id.equals("101") ||
				id.equals("103") || id.equals("105") || id.equals("107") || id.equals("109") ||
				id.equals("111") || id.equals("113") || id.equals("100") || id.equals("102") ||
				id.equals("104") || id.equals("106") || id.equals("108") || id.equals("110") ||
				id.equals("112") || id.equals("114"))
			return (rookCheck(rectangle, map) || bishopCheck(rectangle, map));
		else if (id.equals("7") || id.equals("27") || id.equals("10") || id.equals("30"))
			return knightCheck(rectangle, map);

		return false;
	}

	private boolean knightCheck(Rectangle source, Map<Rectangle, ImageView> map) {
		String kingLocation = "";
		String locationId = map.get(source).getId();
		if (locationId.charAt(locationId.length() - 1) % 2 == 1) {
			kingLocation = giveKingLocation("22", map).getId();
		} else
			kingLocation = giveKingLocation("19", map).getId();
		if (possibleKnightMoves(source.getId()).contains(kingLocation))
			return true;
		return false;
	}

	private boolean isValidSquare(char file, char rank) {
		return file >= 'a' && file <= 'h' && rank >= '1' && rank <= '8';
	}

	public List<String> possibleKnightMoves(String square) {
		List<String> possibleMoves = new ArrayList<>();

		char file = square.charAt(0);
		char rank = square.charAt(1);

		int[] fileOffsets = { -1, -2, -2, -1, 1, 2, 2, 1 };
		int[] rankOffsets = { -2, -1, 1, 2, 2, 1, -1, -2 };

		for (int i = 0; i < 8; i++) {
			char destFile = (char) (file + fileOffsets[i]);
			char destRank = (char) (rank + rankOffsets[i]);

			if (isValidSquare(destFile, destRank)) {
				possibleMoves.add(Character.toString(destFile) + destRank);
			}
		}

		return possibleMoves;
	}

	private boolean bishopCheck(Rectangle source, Map<Rectangle, ImageView> map) {
		String kingLocation = "";
		if (map.get(source).getId().charAt(1) % 2 == 1) {
			kingLocation = giveKingLocation("22", map).getId();
		} else
			kingLocation = giveKingLocation("19", map).getId();
		if (getBishopValidMoves(source.getId()).contains(kingLocation)
				&& !hasImageInBetweenBishop(source.getId(), kingLocation, map))
			return true;
		return false;
	}

	private boolean pawnBlackCheck(Rectangle source, Map<Rectangle, ImageView> map) {
		String kingLocation = "";
		String locationId = map.get(source).getId();
		if (locationId.charAt(locationId.length() - 1) % 2 == 1) {
			kingLocation = giveKingLocation("22", map).getId();
		} else
			kingLocation = giveKingLocation("19", map).getId();
		String square = source.getId();

		char file = square.charAt(0);
		char rank = square.charAt(1);
		char leftFile = (char) (file - 1);
		char rightFile = (char) (file + 1);

		char nextRank = (char) (rank - 1);

		if (leftFile >= 'a') {
			if ((Character.toString(leftFile) + nextRank).equals(kingLocation))
				return true;
		}
		if (rightFile <= 'h') {
			if ((Character.toString(rightFile) + nextRank).equals(kingLocation))
				return true;
		}
		return false;
	}

	private boolean pawnWhiteCheck(Rectangle source, Map<Rectangle, ImageView> map) {
		String kingLocation = "";
		String locationId = map.get(source).getId();
		if (locationId.charAt(locationId.length() - 1) % 2 == 1) {
			kingLocation = giveKingLocation("22", map).getId();
		} else
			kingLocation = giveKingLocation("19", map).getId();
		String square = source.getId();

		char file = square.charAt(0);
		char rank = square.charAt(1);
		char leftFile = (char) (file - 1);
		char rightFile = (char) (file + 1);

		char nextRank = (char) (rank + 1);

		if (leftFile >= 'a') {
			if ((Character.toString(leftFile) + nextRank).equals(kingLocation))
				return true;
		}
		if (rightFile <= 'h') {
			if ((Character.toString(rightFile) + nextRank).equals(kingLocation))
				return true;
		}
		return false;
	}

	private boolean rookCheck(Rectangle source, Map<Rectangle, ImageView> map) {
		String kingLocation = "";
		String locationId = map.get(source).getId();
		if (locationId.charAt(locationId.length() - 1) % 2 == 1) {
			kingLocation = giveKingLocation("22", map).getId();
		} else
			kingLocation = giveKingLocation("19", map).getId();
		String square = source.getId();
		if (possibleRookMoves(square, map, kingLocation).contains(kingLocation))
			return true;
		return false;
	}

	public List<String> possibleRookMoves(String square, Map<Rectangle, ImageView> map, String kingLocation) {
		List<String> possibleMoves = new ArrayList<>();

		char file = square.charAt(0);
		char rank = square.charAt(1);

		// Generate moves in the upward direction
		for (char currentRank = (char) (rank + 1); currentRank <= '8'; currentRank++) {
			if (map.get(giveRectangleById(file + Character.toString(currentRank), map)).getImage().getPixelReader()
					.getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30)
					&& !kingLocation.equals(file + Character.toString(currentRank)))
				break;
			possibleMoves.add(file + Character.toString(currentRank));
		}

		// Generate moves in the downward direction
		for (char currentRank = (char) (rank - 1); currentRank >= '1'; currentRank--) {
			if (map.get(giveRectangleById(file + Character.toString(currentRank), map)).getImage().getPixelReader()
					.getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30)
					&& !kingLocation.equals(file + Character.toString(currentRank)))
				break;
			possibleMoves.add(file + Character.toString(currentRank));
		}

		// Generate moves in the right direction
		for (char currentFile = (char) (file + 1); currentFile <= 'h'; currentFile++) {
			if (map.get(giveRectangleById(Character.toString(currentFile) + rank, map)).getImage().getPixelReader()
					.getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30)
					&& !kingLocation.equals(Character.toString(currentFile) + rank))
				break;
			possibleMoves.add(Character.toString(currentFile) + rank);
		}

		// Generate moves in the left direction
		for (char currentFile = (char) (file - 1); currentFile >= 'a'; currentFile--) {
			if (map.get(giveRectangleById(Character.toString(currentFile) + rank, map)).getImage().getPixelReader()
					.getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30)
					&& !kingLocation.equals(Character.toString(currentFile) + rank))
				break;
			possibleMoves.add(Character.toString(currentFile) + rank);
		}

		return possibleMoves;
	}

	public boolean hasImageInBetweenBishop(String sourceSquare, String destinationSquare,
			Map<Rectangle, ImageView> map) {

		char sourceFile = sourceSquare.charAt(0);
		char sourceRank = sourceSquare.charAt(1);
		char destFile = destinationSquare.charAt(0);
		char destRank = destinationSquare.charAt(1);

		int fileDiff = Math.abs(sourceFile - destFile);
		int rankDiff = Math.abs(sourceRank - destRank);

		if (fileDiff == rankDiff) {
			int fileStep = (destFile > sourceFile) ? 1 : -1;
			int rankStep = (destRank > sourceRank) ? 1 : -1;

			char file = (char) (sourceFile + fileStep);
			char rank = (char) (sourceRank + rankStep);
			while (file != destFile) {
				String square = Character.toString(file) + Character.toString(rank);
				if (checkImagePresent(square, map)) {
					return true;
				}
				file = (char) (file + fileStep);
				rank = (char) (rank + rankStep);
			}
		}

		return false;
	}

	public static List<String> getBishopValidMoves(String square) {
		List<String> possibleMoves = new ArrayList<>();

		char file = square.charAt(0);
		char rank = square.charAt(1);

		char currentFile = file;
		char currentRank = rank;

		while (currentFile < 'h' && currentRank < '8') {
			currentFile++;
			currentRank++;
			possibleMoves.add(Character.toString(currentFile) + currentRank);
		}

		currentFile = file;
		currentRank = rank;
		while (currentFile > 'a' && currentRank < '8') {
			currentFile--;
			currentRank++;
			possibleMoves.add(Character.toString(currentFile) + currentRank);
		}

		currentFile = file;
		currentRank = rank;
		while (currentFile < 'h' && currentRank > '1') {
			currentFile++;
			currentRank--;
			possibleMoves.add(Character.toString(currentFile) + currentRank);
		}

		currentFile = file;
		currentRank = rank;
		while (currentFile > 'a' && currentRank > '1') {
			currentFile--;
			currentRank--;
			possibleMoves.add(Character.toString(currentFile) + currentRank);
		}

		return possibleMoves;
	}

	public boolean checkImagePresent(String id, Map<Rectangle, ImageView> map) {
		if (giveImageById(id, map) == null || map.get(giveImageById(id, map)).getImage().getPixelReader().getArgb(25,
				30) == transparentImg.getPixelReader().getArgb(25, 30))
			return false;
		return true;
	}

	public Rectangle giveImageById(String id, Map<Rectangle, ImageView> map) {
		for (Map.Entry<Rectangle, ImageView> entry : map.entrySet()) {
			Rectangle rectangle = entry.getKey();

			if (rectangle.getId().equals(id)) {
				return rectangle;
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

	public Rectangle giveKingLocation(String id, Map<Rectangle, ImageView> map) {
		for (Map.Entry<Rectangle, ImageView> entry : map.entrySet()) {
			ImageView image = entry.getValue();

			if (image.getId().equals(id)) {
				return entry.getKey();
			}
		}
		return null;
	}

}
