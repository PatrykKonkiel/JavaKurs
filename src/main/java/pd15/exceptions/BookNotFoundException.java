package pd15.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id) {
        super("Book with this id %s does not exist".formatted(id));
    }
}
