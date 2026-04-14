package guess_game.exceptions;

public class PlayerDoesntExist extends RuntimeException {
    public PlayerDoesntExist(String message) {
        super(message);
    }
}
