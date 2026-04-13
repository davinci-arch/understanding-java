package guess_game.exceptions;

public class PlayerAlreadyExists extends RuntimeException {
    public PlayerAlreadyExists(String message) {
        super(message);
    }
}
