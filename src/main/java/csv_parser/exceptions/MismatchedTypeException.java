package csv_parser.exceptions;

public class MismatchedTypeException extends RuntimeException {
    public MismatchedTypeException(String message) {
        super(message);
    }
}
