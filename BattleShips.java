package battleshipgame;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class BattleShips {
    private GameState gameState;
    private Scanner scanner = new Scanner(System.in);

    public BattleShips() {
        this.gameState = new GameState();
    }

	public void play() {
	    System.out.println("Do you want to start a new game or load a saved game? (new/load)");
	    String choice = scanner.nextLine();
	
	    if ("load".equalsIgnoreCase(choice)) {
	        GameState loadedState = GameUtils.loadGame("gamestate.ser");
	        if (loadedState != null) {
	            this.gameState = loadedState;
	            System.out.println("Loaded saved game.");
	        } else {
	            System.out.println("No saved game found. Starting a new game.");
	            initializeNewGame();
	        }
	    } else {
	        initializeNewGame();
	    }
	    while (gameState.getShotsTaken() < 10 && !gameState.getGrid().areAllShipsSunk()) {
	        if (gameState.isDebugMode()) {
	            gameState.getGrid().displayGrid();// To show the grid if in debug mode
	        }
	
	        System.out.println("Enter row and column to fire at (e.g. 5 2):");
	        int row = scanner.nextInt() - 1;// Adjusts for zero-based indexing because of behaviour of arrays
	        int col = scanner.nextInt() - 1;// Adjusts for zero-based indexing because of behaviour of arrays
	
	        if (row < 0 || row >= 10 || col < 0 || col >= 10) {
	            System.out.println("Invalid coordinates. Please try again.");
	            continue;
	        }
	
	        if (gameState.getGrid().hasBeenFiredAt(row, col)) {
	            System.out.println("You have already fired at this square. Try a different one. NOTE This will not be counted as one of your 10 attempts");
	            continue; //Continues with next iteration, and ignores attempt
	        }
	
	        int points = gameState.getGrid().fireAt(row, col);
	        if (points > 0) {
	            System.out.println("You earned " + points + " points!");
	            gameState.setTotalPoints(gameState.getTotalPoints() + points);
	        } else {
	            System.out.println("Miss!");
	        }
	        gameState.setShotsTaken(gameState.getShotsTaken() + 1);
	
	        System.out.println("Do you want to save the game? (yes/no)");
	        String saveChoice = scanner.next();
	        if ("yes".equalsIgnoreCase(saveChoice)) {
	            GameUtils.saveGame(gameState, "gamestate.ser");
	            System.out.println("Game saved.");
	        }
	    }
	
	    System.out.println("Game over! Your score: " + gameState.getTotalPoints());
	}
	    
	private void initializeNewGame() {
	    Grid theGrid = new Grid();
	    List<Ship> ships = createShips();
	    theGrid.placeShipsRandomly(ships);
	    gameState.setGrid(theGrid);
	    gameState.setShips(ships);
	    gameState.setTotalPoints(0);
	    gameState.setShotsTaken(0);
	    askForDebugMode();
	}
	
	private List<Ship> createShips() {
		//  Create ships with their names, sizes and points
	    List<Ship> ships = new ArrayList<>();
	    ships.add(new Ship("Aircraft Carrier", 5, 2)); 
	    ships.add(new Ship("Battleship", 4, 4));  
	    ships.add(new Ship("Submarine", 3, 6)); 
	    ships.add(new Ship("Destroyer", 2, 8)); 
	    ships.add(new Ship("Patrol Boat", 1, 10));
	    return ships;
	}
	
	private void askForDebugMode() {
	    System.out.println("Do you want to play in debug mode? (yes/no)");
	    String response = scanner.next();
	    gameState.setDebugMode(response.equalsIgnoreCase("yes"));
	}
}
