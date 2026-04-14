package guess_game.exceptions;

public class NoSuchPlayerPresent extends RuntimeException {
    public NoSuchPlayerPresent(String message) {
        super(message);
    }
}
