package aoc.day17;

public class NoInputException extends Exception {

    public NoInputException() {
    }

    public NoInputException(String message) {
        super(message);
    }

    public NoInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoInputException(Throwable cause) {
        super(cause);
    }

    public NoInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
