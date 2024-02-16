package battleshipgame;
import java.io.Serializable;

public class Square implements Serializable {  //makes class suitable for serialization
    private static final long serialVersionUID = 1L; // Standard practice for compatibility

    private boolean firedAt;
    private Ship theShip; // Ensure Ship is also Serializable

    public Square() {
        this.firedAt = false;
        this.theShip = null;
    }

    // Check if there's a ship on this square
    public boolean isThereAShip() {
        return theShip != null;
    }

    // Fire at this square
    public void fire() {
        this.firedAt = true;
    }

    // Check if the square has been fired at
    public boolean hasBeenFiredAt() {
        return firedAt;
    }

    // Set a ship on this square
    public void setShip(Ship theShip) {
        this.theShip = theShip;
    }

    // Get the ship on this square
    public Ship getShip() {
        return theShip;
    }

}



    

