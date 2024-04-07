package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.utils.Constants;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

@Service
public class MoveConstraintsServiceImpl {

	private Image transparentImg = new Image(Constants.TRANSPARENT_IMAGE);

	public boolean checkMoveConstraints(Rectangle source, Rectangle destination, ImageView imgView,
			Map<Rectangle, ImageView> map) {
		String id = imgView.getId();
		if (id.equals("3") || id.equals("6") || id.equals("31") || id.equals("34"))
			return rookMoves(source, destination, map);
		else if (id.equals("5") || id.equals("9") || id.equals("13") || id.equals("17") || id.equals("21")
				|| id.equals("25") || id.equals("29") || id.equals("33"))
			return pawnMovesWhite(source, destination, map);
		else if (id.equals("4") || id.equals("8") || id.equals("12") || id.equals("16") || id.equals("20")
				|| id.equals("24") || id.equals("28") || id.equals("32"))
			return pawnMovesBlack(source, destination, map);
		else if (id.equals("11") || id.equals("23") || id.equals("14") || id.equals("26"))
			return bishopMoves(source, destination, map);
		else if (id.equals("15") || id.equals("18") || id.equals("99") || id.equals("100"))
			return (rookMoves(source, destination, map) || bishopMoves(source, destination, map));
		else if (id.equals("7") || id.equals("27") || id.equals("10") || id.equals("30")) return knightMoves(source, destination);
		else if (id.equals("19") || id.equals("22")) return kingMoves(source, destination);

		return false;
	}

	private boolean kingMoves(Rectangle source, Rectangle destination) {

		List<String> destList = new ArrayList<>();
		destList.addAll(possibleKingMoves(source.getId()));

		if (destList.contains(destination.getId()))
			return true;
		return false;
	}

	private boolean knightMoves(Rectangle source, Rectangle destination) {

		List<String> destList = new ArrayList<>();
		destList.addAll(possibleKnightMoves(source.getId()));

		if (destList.contains(destination.getId()))
			return true;
		return false;
	}

	private boolean bishopMoves(Rectangle source, Rectangle destination, Map<Rectangle, ImageView> map) {
		
		List<String> destList = new ArrayList<>();
		destList.addAll(possibleBishopMoves(source.getId()));

		if (destList.contains(destination.getId()) && !hasImageInBetween(source.getId(), destination.getId(), map))
			return true;
		return false;
	}

	public boolean rookMoves(Rectangle source, Rectangle destination, Map<Rectangle, ImageView> map) {
		char idLetter = source.getId().charAt(0);
		char idNum = source.getId().charAt(1);
		List<String> destList = new ArrayList<>();
		destList.addAll(Arrays.asList(idLetter + "1", idLetter + "2", idLetter + "3", idLetter + "4", idLetter + "5",
				idLetter + "6", idLetter + "7", idLetter + "8"));
		destList.addAll(Arrays.asList("a" + idNum, "b" + idNum, "c" + idNum, "d" + idNum, "e" + idNum, "f" + idNum,
				"g" + idNum, "h" + idNum));
		int sourceId = Integer.parseInt(source.getId().substring(1));
		int destinationId = Integer.parseInt(destination.getId().substring(1));
		char sourceIdChar = source.getId().charAt(0);
		char destinationIdChar = destination.getId().charAt(0);

		int i = destinationId - sourceId;
		boolean noHindrance = true;

		if (i != 0) {
			int j = 2;
			while (j < 8) {
				if ((destinationId < j && j < sourceId) || (destinationId > j && j > sourceId)) {
					if (map.get(giveImageById(idLetter + Integer.toString(j), map)).getImage().getPixelReader()
							.getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30))
						noHindrance = false;
				}
				j++;
			}
		} else {
			int j = 98;
			while (j < 104) {
				if (((int) destinationIdChar < j && j < (int) sourceIdChar)
						|| ((int) destinationIdChar > j && j > (int) sourceIdChar)) {
					if (map.get(giveImageById(((char) j) + String.valueOf(idNum), map)).getImage().getPixelReader()
							.getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30))
						noHindrance = false;
				}
				j++;
			}
		}
		if (destList.contains(destination.getId()) && noHindrance)
			return true;
		return false;
	}

	public boolean pawnMovesWhite(Rectangle source, Rectangle destination, Map<Rectangle, ImageView> map) {
		char idLetter = source.getId().charAt(0);
		char idNum = source.getId().charAt(1);
		List<String> destList = new ArrayList<>();
		boolean noHindrance = true;
		boolean noCaptureHindrance = false;

		destList.addAll(Arrays.asList(idLetter + Integer.toString(Character.getNumericValue(idNum) + 1)));
		if (map.get(giveImageById(idLetter + Integer.toString(Character.getNumericValue(idNum) + 1), map)).getImage()
				.getPixelReader().getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30)) {
			noHindrance = false;
			destList.removeAll(List.of(idLetter + Integer.toString(Character.getNumericValue(idNum) + 1)));
		}

		if (Character.getNumericValue(idNum) == 2) {
			destList.addAll(Arrays.asList(idLetter + String.valueOf(Character.getNumericValue(idNum) + 2)));
			if (map.get(giveImageById(idLetter + Integer.toString(Character.getNumericValue(idNum) + 2), map))
					.getImage().getPixelReader().getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30)
					|| !noHindrance) {
				destList.removeAll(List.of(idLetter + Integer.toString(Character.getNumericValue(idNum) + 2)));
			}

		}
		String str = idLetter + Integer.toString(Character.getNumericValue(idNum) + 1);
		String rightId = str;
		String leftId = str;

		if ((int) (str.charAt(0)) < 104)
			rightId = str.replace(str.charAt(0), (char) ((int) (str.charAt(0)) + 1));
		if ((int) (str.charAt(0)) > 97)
			leftId = str.replace(str.charAt(0), (char) ((int) (str.charAt(0)) - 1));

		if (map.get(giveImageById(rightId, map)).getImage().getPixelReader().getArgb(25, 30) != transparentImg
				.getPixelReader().getArgb(25, 30)) {
			destList.add(rightId);
			noCaptureHindrance = true;
		}
		if (map.get(giveImageById(leftId, map)).getImage().getPixelReader().getArgb(25, 30) != transparentImg
				.getPixelReader().getArgb(25, 30)) {
			destList.add(leftId);
			noCaptureHindrance = true;
		}
		if (destList.contains(destination.getId()) && (noHindrance || noCaptureHindrance))
			return true;

		return false;
	}

	public boolean pawnMovesBlack(Rectangle source, Rectangle destination, Map<Rectangle, ImageView> map) {
		char idLetter = source.getId().charAt(0);
		char idNum = source.getId().charAt(1);
		List<String> destList = new ArrayList<>();
		boolean noHindrance = true;
		boolean noCaptureHindrance = false;

		destList.addAll(Arrays.asList(idLetter + Integer.toString(Character.getNumericValue(idNum) - 1)));
		if (map.get(giveImageById(idLetter + Integer.toString(Character.getNumericValue(idNum) - 1), map)).getImage()
				.getPixelReader().getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30)) {
			noHindrance = false;
			destList.removeAll(List.of(idLetter + Integer.toString(Character.getNumericValue(idNum) - 1)));
		}
		if (Character.getNumericValue(idNum) == 7) {
			destList.addAll(Arrays.asList(idLetter + String.valueOf(Character.getNumericValue(idNum) - 2)));
			if (map.get(giveImageById(idLetter + Integer.toString(Character.getNumericValue(idNum) - 2), map))
					.getImage().getPixelReader().getArgb(25, 30) != transparentImg.getPixelReader().getArgb(25, 30)
					|| !noHindrance) {
				destList.removeAll(List.of(idLetter + Integer.toString(Character.getNumericValue(idNum) - 2)));
			}

		}
		String str = idLetter + Integer.toString(Character.getNumericValue(idNum) - 1);
		String rightId = str;
		String leftId = str;

		if ((int) (str.charAt(0)) < 104)
			rightId = str.replace(str.charAt(0), (char) ((int) (str.charAt(0)) + 1));
		if ((int) (str.charAt(0)) > 97)
			leftId = str.replace(str.charAt(0), (char) ((int) (str.charAt(0)) - 1));

		if (map.get(giveImageById(rightId, map)).getImage().getPixelReader().getArgb(25, 30) != transparentImg
				.getPixelReader().getArgb(25, 30)) {
			destList.add(rightId);
			noCaptureHindrance = true;
		}
		if (map.get(giveImageById(leftId, map)).getImage().getPixelReader().getArgb(25, 30) != transparentImg
				.getPixelReader().getArgb(25, 30)) {
			destList.add(leftId);
			noCaptureHindrance = true;
		}
		if (destList.contains(destination.getId()) && (noHindrance || noCaptureHindrance))
			return true;

		return false;
	}

	public static List<String> possibleBishopMoves(String square) {
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

	public boolean hasImageInBetween(String sourceSquare, String destinationSquare, Map<Rectangle, ImageView> map) {

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
	
	public List<String> possibleKingMoves(String square) {
        List<String> possibleMoves = new ArrayList<>();

        char file = square.charAt(0);
        char rank = square.charAt(1);

        int[] fileOffsets = { -1, 0, 1, -1, 1, -1, 0, 1 };
        int[] rankOffsets = { -1, -1, -1, 0, 0, 1, 1, 1 };

        for (int i = 0; i < 8; i++) {
            char destFile = (char) (file + fileOffsets[i]);
            char destRank = (char) (rank + rankOffsets[i]);

            if (isValidSquare(destFile, destRank)) {
                possibleMoves.add(Character.toString(destFile) + destRank);
            }
        }

        return possibleMoves;
    }

	private boolean isValidSquare(char file, char rank) {
		return file >= 'a' && file <= 'h' && rank >= '1' && rank <= '8';
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
}
