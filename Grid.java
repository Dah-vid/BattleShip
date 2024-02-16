package battleshipgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.Serializable;

public class Grid implements Serializable {//makes grid serializable
	private static final long serialVersionUID = 1L; //standard practice for version control, ensuring it compatible

	private ArrayList<Row> theRows;
	private final int NUMBER_OF_ROWS = 10;
	private final int NUMBER_OF_COLUMNS = 10;

	public Grid() {
		theRows = new ArrayList<>();
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			theRows.add(new Row(i, NUMBER_OF_COLUMNS));
		}
	}

	public void placeShipsRandomly(List<Ship> ships) {
		Random rand = new Random();
		for (Ship ship : ships) {
			boolean placed = false;
			while (!placed) {
				int row = rand.nextInt(NUMBER_OF_ROWS);
				int col = rand.nextInt(NUMBER_OF_COLUMNS);
				boolean horizontal = rand.nextBoolean();

				if (canPlaceShip(ship, row, col, horizontal)) {
					placeShip(ship, row, col, horizontal);
					placed = true;
				}
			}
		}
	}

	private boolean canPlaceShip(Ship ship, int row, int col, boolean horizontal) {
		int length = ship.getLength();

		if (horizontal) {
			if (col + length > NUMBER_OF_COLUMNS)
				return false;
			for (int i = 0; i < length; i++) {
				if (theRows.get(row).getSquare(col + i).isThereAShip()) {
					return false;
				}
			}
		} else {
			if (row + length > NUMBER_OF_ROWS)
				return false;
			for (int i = 0; i < length; i++) {
				if (theRows.get(row + i).getSquare(col).isThereAShip()) {
					return false;
				}
			}
		}
		return true;
	}

	private void placeShip(Ship ship, int row, int col, boolean horizontal) {
		if (horizontal) {
			for (int i = 0; i < ship.getLength(); i++) {
				theRows.get(row).getSquare(col + i).setShip(ship);
				ship.addCoordinate(row, col + i);
			}
		} else {
			for (int i = 0; i < ship.getLength(); i++) {
				theRows.get(row + i).getSquare(col).setShip(ship);
				ship.addCoordinate(row + i, col);
			}
		}
	}

	public int fireAt(int row, int col) {
		Square targetSquare = theRows.get(row).getSquare(col);
		if (targetSquare.hasBeenFiredAt()) {
			return 0;
		}
		targetSquare.fire();

		if (targetSquare.isThereAShip()) {
			Ship hitShip = targetSquare.getShip();
			int points = hitShip.getPoints();

			// Displays the name of the ship that was hit
			System.out.println("Hit! You've sunk the " + hitShip.getName() + "!");

			// This destroys the entire ship
			for (Integer shipRow : hitShip.getRows()) {
				for (Integer shipCol : hitShip.getCols()) {
					theRows.get(shipRow).getSquare(shipCol).setShip(null);
				}
			}

			return points;
		} else {
			return 0; // It's a miss
		}
	}

	// Checks if square has been fired on
	public boolean hasBeenFiredAt(int row, int col) {
		return theRows.get(row).getSquare(col).hasBeenFiredAt();
	}

	public boolean areAllShipsSunk() {
		for (Row row : theRows) {
			for (Square square : row.getTheSquares()) {
				if (square.isThereAShip()) {
					return false;
				}
			}
		}
		return true;
	}

	public void displayGrid() {
		for (Row row : theRows) {
			for (Square square : row.getTheSquares()) {
				if (square.isThereAShip()) {
					System.out.print("X ");
				} else {
					System.out.print(". ");
				}
			}
			System.out.println();
		}
	}
}
