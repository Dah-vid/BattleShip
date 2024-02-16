package battleshipgame;
import java.util.ArrayList;
import java.io.Serializable;

public class Ship implements Serializable { //makes class serializable
    private static final long serialVersionUID = 1L; // standard practice to ensure compatibility between different versions of the class

    private String name;
    private int length;
    private int points;
    private ArrayList<Integer> rows; // Stores the row indices of the ship's position
    private ArrayList<Integer> cols; // Stores the column indices of the ship's position

    public Ship(String name, int length, int points) {
        this.name = name;
        this.length = length;
        this.points = points;
        this.rows = new ArrayList<>();
        this.cols = new ArrayList<>();
    }

    // Add a coordinate to the ship
    public void addCoordinate(int row, int col) {
        rows.add(row);
        cols.add(col);
    }

    // Check if the ship occupies a given square
    public boolean occupiesSquare(int row, int col) {
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i) == row && cols.get(i) == col) {
                return true;
            }
        }
        return false;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getPoints() {
        return points;
    }

    public ArrayList<Integer> getRows() {
        return rows;
    }

    public ArrayList<Integer> getCols() {
        return cols;
    }
}

