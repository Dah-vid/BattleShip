package battleshipgame;
import java.io.Serializable;
import java.util.ArrayList;

public class Row implements Serializable { //makes row serializable
    private static final long serialVersionUID = 1L; // Standard version control practice for compatibility

    private ArrayList<Square> theSquares;
    private int rowNumber;

    public Row(int rowNumber, int numberOfSquares) {
        this.rowNumber = rowNumber;
        this.theSquares = new ArrayList<>();

        for (int i = 0; i < numberOfSquares; i++) {
            theSquares.add(new Square());
        }
    }

    // Get a specific square from the row
    public Square getSquare(int index) {
        return theSquares.get(index);
    }

    // Get all the squares in the row
    public ArrayList<Square> getTheSquares() {
        return theSquares;
    }

    // Get the row number
    public int getRowNumber() {
        return rowNumber;
    }

}
