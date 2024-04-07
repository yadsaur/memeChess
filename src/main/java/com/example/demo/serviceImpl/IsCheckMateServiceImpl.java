package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.utils.Constants;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

@Service
public class IsCheckMateServiceImpl {

	private Image transparentImg = new Image(Constants.TRANSPARENT_IMAGE);

	public boolean checkIfCheckMateDouble(Map<Rectangle, ImageView> map) {
		Rectangle kingLocation;
		String pieceColor = "";

		Rectangle checkingPiece = giveRectangleByImageId(Constants.CHECKING_PIECE, map);
		if (Constants.CHECKING_PIECE.charAt(Constants.CHECKING_PIECE.length() - 1) % 2 != 1)
			pieceColor = "white";
		else
			pieceColor = "black";
		kingLocation = giveKingLocation(pieceColor, map);
		return kingEscapePossible(checkingPiece, kingLocation, map, pieceColor);

	}

	public boolean checkIfCheckMateDiscovered(Map<Rectangle, ImageView> map) {
		Rectangle checkingPiece = giveRectangleByImageId(Constants.CHECKING_PIECE, map);
		return checkIfCheckMate(checkingPiece, map);
	}

	public boolean checkIfCheckMate(Rectangle source, Map<Rectangle, ImageView> map) {
		Rectangle kingLocation;
		String pieceColor = "";
		String locationId = map.get(source).getId();
		if (locationId.charAt(locationId.length() - 1) % 2 == 1)
			pieceColor = "white";
		else
			pieceColor = "black";
		kingLocation = giveKingLocation(pieceColor, map);
		List<String> squareList = new ArrayList<>();
		squareList.add(source.getId());
		if (getSquareBetweenKingAndPiece(source, kingLocation, map) != null)
			squareList.addAll(getSquareBetweenKingAndPiece(source, kingLocation, map));

		boolean kingEscapePossible = kingEscapePossible(source, kingLocation, map, pieceColor);
		if (!getIfBlockPossible(source, pieceColor, squareList, map) && !kingEscapePossible)
			return true;
		return false;
	}

	private boolean kingEscapePossible(Rectangle source, Rectangle kingLocation, Map<Rectangle, ImageView> map,
			String pieceColor) {
		List<String> notSafeSquares = new ArrayList<>();
		String id = map.get(source).getId();
		String piecePosition = source.getId();
		String kingPosition = kingLocation.getId();
		if (id.equals("3") || id.equals("6") || id.equals("31") || id.equals("34"))
			notSafeSquares.addAll(getGenerallyPossibleRookMoves(piecePosition));
		else if (id.equals("11") || id.equals("23") || id.equals("14") || id.equals("26"))
			notSafeSquares.addAll(getGenerallyPossibleBishopMoves(piecePosition));
		else if (id.equals("15") || id.equals("18") || id.equals("99") || id.equals("101") || id.equals("103")
				|| id.equals("105") || id.equals("107") || id.equals("109") || id.equals("111") || id.equals("113")
				|| id.equals("100") || id.equals("102") || id.equals("104") || id.equals("106") || id.equals("108")
				|| id.equals("110") || id.equals("112") || id.equals("114")) {
			notSafeSquares.addAll(getGenerallyPossibleBishopMoves(piecePosition));
			notSafeSquares.addAll(getGenerallyPossibleRookMoves(piecePosition));
		} else if (id.equals("7") || id.equals("27") || id.equals("10") || id.equals("30"))
			notSafeSquares.addAll(possibleKnightMoves(piecePosition));

		return doesNotContainsAny(notSafeSquares, getPossibleKingMoves(kingPosition, map, pieceColor, notSafeSquares));
	}

	private List<String> getGenerallyPossibleBlackPawnMoves(String id, Map<Rectangle, ImageView> map) {
		List<String> possibleMoves = new ArrayList<>();
		String piecePosition = giveRectangleByImageId(id, map).getId();

		char file = piecePosition.charAt(0);
		char rank = piecePosition.charAt(1);
		rank = (char) (rank - 1);
		possibleMoves.add(Character.toString(file + 1) + rank);
		possibleMoves.add(Character.toString(file - 1) + rank);
		return possibleMoves;
	}

	private List<String> getGenerallyPossibleWhitePawnMoves(String id, Map<Rectangle, ImageView> map) {
		List<String> possibleMoves = new ArrayList<>();
		String piecePosition = giveRectangleByImageId(id, map).getId();

		char file = piecePosition.charAt(0);
		char rank = piecePosition.charAt(1);
		rank = (char) (rank + 1);
		possibleMoves.add(Character.toString(file + 1) + rank);
		possibleMoves.add(Character.toString(file - 1) + rank);
		return possibleMoves;
	}

	private List<String> getPossibleKingMoves(String square, Map<Rectangle, ImageView> map, String pieceColor,
			List<String> notSafeSquares) {
		List<String> possibleMoves = new ArrayList<>();
		List<String> validPossibleMoves = new ArrayList<>();
		Integer myPiece = 1;
		if (pieceColor.equals("white"))
			myPiece = 0;
		char file = square.charAt(0);
		char rank = square.charAt(1);

		possibleMoves.addAll(Arrays.asList(file + Character.toString(rank + 1), file + Character.toString(rank - 1),
				Character.toString(file + 1) + rank, Character.toString(file - 1) + rank,
				Character.toString(file + 1) + Character.toString(rank + 1),
				Character.toString(file - 1) + Character.toString(rank - 1),
				Character.toString(file - 1) + Character.toString(rank + 1),
				Character.toString(file + 1) + Character.toString(rank - 1)));
		for (int i = 0; i < possibleMoves.size(); i++) {
			if (isValidSquare(possibleMoves.get(i).charAt(0), possibleMoves.get(i).charAt(1))
					&& Integer.parseInt(map.get(giveRectangleById(possibleMoves.get(i), map)).getId()) % 2 != myPiece
					&& !notSafeSquares.contains(possibleMoves.get(i))
					&& !new PieceEyesServiceImpl().checkIfEyes(possibleMoves.get(i), pieceColor, map))
				validPossibleMoves.add(possibleMoves.get(i));
		}
		return validPossibleMoves;
	}

	private boolean getIfBlockPossible(Rectangle source, String pieceColor, List<String> squareList,
			Map<Rectangle, ImageView> map) {
		List<String> piecesList = new ArrayList<>();
		List<String> possibleMoves = new ArrayList<>();
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

		if (pieceColor.equals("white")) {
			piecesList.addAll(Arrays.asList("4", "8", "12", "16", "20", "24", "28", "32", "6", "10", "14", "18", "22",
					"26", "30", "34"));
			if (!blackQueenMoves.isEmpty())
				piecesList.addAll(blackQueenMoves);
		}

		else {
			piecesList.addAll(Arrays.asList("5", "9", "13", "17", "21", "25", "29", "33", "3", "7", "11", "15", "19",
					"23", "27", "31"));
			if (!whiteQueenMoves.isEmpty())
				piecesList.addAll(whiteQueenMoves);
		}
		possibleMoves = getAllMovesList(source, piecesList, map);
		if (containsAny(possibleMoves, squareList))
			return true;
		return false;
	}

	public boolean doesNotContainsAny(List<String> list1, List<String> list2) {
		for (String element : list2) {
			if (!list1.contains(element)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsAny(List<String> list1, List<String> list2) {
		for (String element : list2) {
			if (list1.contains(element)) {
				return true;
			}
		}
		return false;
	}

	private List<String> getAllMovesList(Rectangle source, List<String> piecesList, Map<Rectangle, ImageView> map) {
		List<String> allPosiibleMoves = new ArrayList<>();
		for (int i = 0; i < piecesList.size(); i++) {
			String id = piecesList.get(i);
			Rectangle positionRectangle = givePiecePosition(id, map);
			if (positionRectangle == null)
				continue;
			String piecePosition = positionRectangle.getId();
			if (id.equals("3") || id.equals("6") || id.equals("31") || id.equals("34"))
				allPosiibleMoves.addAll(possibleRookMoves(source, piecePosition, map));
			else if (id.equals("5") || id.equals("9") || id.equals("13") || id.equals("17") || id.equals("21")
					|| id.equals("25") || id.equals("29") || id.equals("33"))
				allPosiibleMoves.addAll(possibleWhitePawnMoves(source, piecePosition));
			else if (id.equals("4") || id.equals("8") || id.equals("12") || id.equals("16") || id.equals("20")
					|| id.equals("24") || id.equals("28") || id.equals("32"))
				allPosiibleMoves.addAll(possibleBlackPawnMoves(source, piecePosition));
			else if (id.equals("11") || id.equals("23") || id.equals("14") || id.equals("26"))
				allPosiibleMoves.addAll(possibleBishopMoves(source, piecePosition, map));
			else if (id.equals("15") || id.equals("18") || id.equals("99") || id.equals("101") || id.equals("103")
					|| id.equals("105") || id.equals("107") || id.equals("109") || id.equals("111") || id.equals("113")
					|| id.equals("100") || id.equals("102") || id.equals("104") || id.equals("106") || id.equals("108")
					|| id.equals("110") || id.equals("112") || id.equals("114")) {
				allPosiibleMoves.addAll(possibleBishopMoves(source, piecePosition, map));
				allPosiibleMoves.addAll(possibleRookMoves(source, piecePosition, map));
			} else if (id.equals("7") || id.equals("27") || id.equals("10") || id.equals("30"))
				allPosiibleMoves.addAll(possibleKnightMoves(piecePosition));
		}
		return allPosiibleMoves;
	}

	private Rectangle givePiecePosition(String id, Map<Rectangle, ImageView> map) {
		for (Map.Entry<Rectangle, ImageView> entry : map.entrySet()) {
			ImageView image = entry.getValue();

			if (image.getId().equals(id)) {
				return entry.getKey();
			}
		}
		return null;
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

	public List<String> possibleRookMoves(Rectangle source, String square, Map<Rectangle, ImageView> map) {
		List<String> possibleMoves = new ArrayList<>();

		char file = square.charAt(0);
		char rank = square.charAt(1);

		// Generate moves in the upward direction
		for (char currentRank = (char) (rank + 1); currentRank <= '8'; currentRank++) {
			if (!source.getId().equals(file + Character.toString(currentRank))
					&& map.get(giveRectangleById(file + Character.toString(currentRank), map)).getImage()
							.getPixelReader().getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30))
				break;
			possibleMoves.add(file + Character.toString(currentRank));
		}

		// Generate moves in the downward direction
		for (char currentRank = (char) (rank - 1); currentRank >= '1'; currentRank--) {
			if (!source.getId().equals(file + Character.toString(currentRank))
					&& map.get(giveRectangleById(file + Character.toString(currentRank), map)).getImage()
							.getPixelReader().getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30))
				break;
			possibleMoves.add(file + Character.toString(currentRank));
		}

		// Generate moves in the right direction
		for (char currentFile = (char) (file + 1); currentFile <= 'h'; currentFile++) {
			if (!source.getId().equals(Character.toString(currentFile) + rank)
					&& map.get(giveRectangleById(Character.toString(currentFile) + rank, map)).getImage()
							.getPixelReader().getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30))
				break;
			possibleMoves.add(Character.toString(currentFile) + rank);
		}

		// Generate moves in the left direction
		for (char currentFile = (char) (file - 1); currentFile >= 'a'; currentFile--) {
			if (!source.getId().equals(Character.toString(currentFile) + rank)
					&& map.get(giveRectangleById(Character.toString(currentFile) + rank, map)).getImage()
							.getPixelReader().getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30))
				break;
			possibleMoves.add(Character.toString(currentFile) + rank);
		}

		return possibleMoves;
	}

	public List<String> possibleBlackPawnMoves(Rectangle source, String square) {
		List<String> possibleMoves = new ArrayList<>();
		String sourceLocation = source.getId();
		char file = square.charAt(0);
		char rank = square.charAt(1);

		// Single move forward
		char nextRank = (char) (rank - 1);
		if (nextRank >= '1') {
			if (!(file + Character.toString(nextRank)).equals(sourceLocation))
				possibleMoves.add(file + Character.toString(nextRank));
		}

		// Double move forward from the starting position
		if (rank == '7') {
			char doubleRank = (char) (rank - 2);
			if (!(file + Character.toString(doubleRank)).equals(sourceLocation))
				possibleMoves.add(file + Character.toString(doubleRank));
		}

		// Capture moves
		char leftFile = (char) (file - 1);
		char rightFile = (char) (file + 1);
		if (leftFile >= 'a') {
			if ((Character.toString(leftFile) + nextRank).equals(sourceLocation))
				possibleMoves.add(Character.toString(leftFile) + nextRank);
		}
		if (rightFile <= 'h') {
			if ((Character.toString(rightFile) + nextRank).equals(sourceLocation))
				possibleMoves.add(Character.toString(rightFile) + nextRank);
		}

		return possibleMoves;
	}

	public List<String> possibleWhitePawnMoves(Rectangle source, String square) {
		List<String> possibleMoves = new ArrayList<>();
		String sourceLocation = source.getId();

		char file = square.charAt(0);
		char rank = square.charAt(1);

		// Single move forward
		char nextRank = (char) (rank + 1);
		if (nextRank <= '8') {
			if (!(file + Character.toString(nextRank)).equals(sourceLocation))
				possibleMoves.add(file + Character.toString(nextRank));
		}

		// Double move forward from the starting position
		if (rank == '2') {
			char doubleRank = (char) (rank + 2);
			if (!(file + Character.toString(doubleRank)).equals(sourceLocation))
				possibleMoves.add(file + Character.toString(doubleRank));
		}

		// Capture moves
		char leftFile = (char) (file - 1);
		char rightFile = (char) (file + 1);
		if (leftFile >= 'a') {
			if ((Character.toString(leftFile) + nextRank).equals(sourceLocation))
				possibleMoves.add(Character.toString(leftFile) + nextRank);
		}
		if (rightFile <= 'h') {
			if ((Character.toString(rightFile) + nextRank).equals(sourceLocation))
				possibleMoves.add(Character.toString(rightFile) + nextRank);
		}
		return possibleMoves;
	}

	public List<String> possibleBishopMoves(Rectangle source, String square, Map<Rectangle, ImageView> map) {
		List<String> possibleMoves = new ArrayList<>();

		char file = square.charAt(0);
		char rank = square.charAt(1);

		char currentFile = file;
		char currentRank = rank;

		while (currentFile < 'h' && currentRank < '8') {
			currentFile++;
			currentRank++;
			if (!source.getId().equals(Character.toString(currentFile) + currentRank)
					&& map.get(giveRectangleById(Character.toString(currentFile) + currentRank, map)).getImage()
							.getPixelReader().getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30))
				break;
			possibleMoves.add(Character.toString(currentFile) + currentRank);
		}

		currentFile = file;
		currentRank = rank;
		while (currentFile > 'a' && currentRank < '8') {
			currentFile--;
			currentRank++;
			if (!source.getId().equals(Character.toString(currentFile) + currentRank)
					&& map.get(giveRectangleById(Character.toString(currentFile) + currentRank, map)).getImage()
							.getPixelReader().getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30))
				break;
			possibleMoves.add(Character.toString(currentFile) + currentRank);
		}

		currentFile = file;
		currentRank = rank;
		while (currentFile < 'h' && currentRank > '1') {
			currentFile++;
			currentRank--;
			if (!source.getId().equals(Character.toString(currentFile) + currentRank)
					&& map.get(giveRectangleById(Character.toString(currentFile) + currentRank, map)).getImage()
							.getPixelReader().getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30))
				break;
			possibleMoves.add(Character.toString(currentFile) + currentRank);
		}

		currentFile = file;
		currentRank = rank;
		while (currentFile > 'a' && currentRank > '1') {
			currentFile--;
			currentRank--;
			if (!source.getId().equals(Character.toString(currentFile) + currentRank)
					&& map.get(giveRectangleById(Character.toString(currentFile) + currentRank, map)).getImage()
							.getPixelReader().getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30))
				break;
			possibleMoves.add(Character.toString(currentFile) + currentRank);
		}

		return possibleMoves;
	}

	private List<String> getSquareBetweenKingAndPiece(Rectangle source, Rectangle kingLocation,
			Map<Rectangle, ImageView> map) {
		String id = map.get(source).getId();
		if (id.equals("3") || id.equals("6") || id.equals("31") || id.equals("34"))
			return getSquareBetweenKingAndPieceRook(source, kingLocation);
		else if (id.equals("11") || id.equals("23") || id.equals("14") || id.equals("26"))
			return getSquareBetweenKingAndPieceBishop(source, kingLocation);
		else if (id.equals("15") || id.equals("18")) {
			List<String> bishopInBetweenSquare = getSquareBetweenKingAndPieceBishop(source, kingLocation);
			if (bishopInBetweenSquare != null)
				return getSquareBetweenKingAndPieceBishop(source, kingLocation);
			return getSquareBetweenKingAndPieceRook(source, kingLocation);
		}
		return null;
	}

	private List<String> getSquareBetweenKingAndPieceRook(Rectangle source, Rectangle kingLocation) {
		List<String> squares = new ArrayList<>();

		char rookCol = source.getId().charAt(0);
		int rookRow = Integer.parseInt(source.getId().substring(1));
		char kingCol = kingLocation.getId().charAt(0);
		int kingRow = Integer.parseInt(kingLocation.getId().substring(1));

		if (rookCol == kingCol) {
			for (int i = rookRow + 1; i < kingRow; i++) {
				squares.add(rookCol + String.valueOf(i));
			}
		} else if (rookRow == kingRow) {
			for (char c = (char) (rookCol + 1); c < kingCol; c++) {
				squares.add(c + String.valueOf(rookRow));
			}
		} else {
			return squares;
		}

		return squares;
	}

	private List<String> getSquareBetweenKingAndPieceBishop(Rectangle source, Rectangle kingLocation) {

		char sourceFile = source.getId().charAt(0);
		char sourceRank = source.getId().charAt(1);
		char destFile = kingLocation.getId().charAt(0);
		char destRank = kingLocation.getId().charAt(1);

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
				squaresList.add(square);

				file = (char) (file + fileStep);
				rank = (char) (rank + rankStep);
			}
		}
		if (!squaresList.isEmpty())
			return squaresList;

		return null;
	}

	public static List<String> getGenerallyPossibleBishopMoves(String square) {
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

	public static List<String> getGenerallyPossibleRookMoves(String square) {
		List<String> possibleMoves = new ArrayList<>();

		char file = square.charAt(0);
		char rank = square.charAt(1);

		// Generate moves in the upward direction
		for (char currentRank = (char) (rank + 1); currentRank <= '8'; currentRank++) {
			possibleMoves.add(file + Character.toString(currentRank));
		}

		// Generate moves in the downward direction
		for (char currentRank = (char) (rank - 1); currentRank >= '1'; currentRank--) {
			possibleMoves.add(file + Character.toString(currentRank));
		}

		// Generate moves in the right direction
		for (char currentFile = (char) (file + 1); currentFile <= 'h'; currentFile++) {
			possibleMoves.add(Character.toString(currentFile) + rank);
		}

		// Generate moves in the left direction
		for (char currentFile = (char) (file - 1); currentFile >= 'a'; currentFile--) {
			possibleMoves.add(Character.toString(currentFile) + rank);
		}

		return possibleMoves;
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

	public Rectangle giveKingLocation(String pieceColor, Map<Rectangle, ImageView> map) {
		String id = "";
		if (pieceColor.equals("white"))
			id = "22";
		else
			id = "19";
		for (Map.Entry<Rectangle, ImageView> entry : map.entrySet()) {
			ImageView image = entry.getValue();

			if (image.getId().equals(id)) {
				return entry.getKey();
			}
		}
		return null;
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

}
