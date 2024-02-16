package battleshipgame;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.Serializable;


public class GameUtils {

    // Method to save the game state to a file
    public static void saveGame(GameState gameState, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(gameState);
        } catch (IOException e) {
            System.err.println("Error saving the game: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to load the game state from a file
    public static GameState loadGame(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (GameState) in.readObject();
        } catch (IOException e) {
            System.err.println("Error loading the game: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
