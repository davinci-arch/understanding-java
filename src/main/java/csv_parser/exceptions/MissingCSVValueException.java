package csv_parser.exceptions;

public class MissingCSVValueException extends RuntimeException {
    public MissingCSVValueException(String message) {
        super(message);
    }
}
