
package battleshipgame;
import java.io.Serializable;
import java.util.List;

public class GameState implements Serializable {
    private Grid grid;
    private List<Ship> ships;
    private int totalPoints;
    private int shotsTaken;
    private boolean debugMode;

   
    public GameState() {
    }

    public GameState(Grid grid, List<Ship> ships, int totalPoints, int shotsTaken, boolean debugMode) {
        this.grid = grid;
        this.ships = ships;
        this.totalPoints = totalPoints;
        this.shotsTaken = shotsTaken;
        this.debugMode = debugMode;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getShotsTaken() {
        return shotsTaken;
    }

    public void setShotsTaken(int shotsTaken) {
        this.shotsTaken = shotsTaken;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}


