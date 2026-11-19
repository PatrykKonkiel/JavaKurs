package pd15.exceptions;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException() {
        super("Book is not available");
    }
}
