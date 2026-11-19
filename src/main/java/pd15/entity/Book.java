package pd15.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import pd15.BookStatus;
import pd15.exceptions.BookNotAvailableException;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@ToString
@Getter
public class Book {
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private int year;
    private BookStatus bookStatus;
    private int availableCopies;

    private static final AtomicLong counter = new AtomicLong(0);

    public Book(String isbn, String title, String author, int year, int availableCopies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.availableCopies = availableCopies;
        bookStatus = BookStatus.LOANED;
        id = counter.incrementAndGet();
    }


    public void borrow() {
        if (availableCopies <= 0) {
            throw new BookNotAvailableException();
        }
        availableCopies--;
    }

    public void returnBack() {
        bookStatus = BookStatus.RETURNED;
        availableCopies++;
    }

}
