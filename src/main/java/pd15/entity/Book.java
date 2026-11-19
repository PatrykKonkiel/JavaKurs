package pd15.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pd15.exceptions.BookNotAvailableException;
@Getter
@AllArgsConstructor(staticName = "of")
public class Book {
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private int year;
    private int availableCopies;

    public void borrow() {
        if (availableCopies <= 0) {
            throw new BookNotAvailableException("Book is not available");
        }
        availableCopies--;
    }

    public void returnBack() {
        availableCopies++;
    }

}
