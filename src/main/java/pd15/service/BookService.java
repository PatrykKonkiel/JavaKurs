package pd15.service;

import lombok.AllArgsConstructor;
import pd15.LibraryData;
import pd15.dto.BookDto;
import pd15.entity.Book;
import pd15.exceptions.BookNotFoundException;

import java.util.List;

@AllArgsConstructor(staticName = "of")
public class BookService {
    private LibraryData data;

    public BookDto addBook(BookDto dto) {
        Book book = Book.of(
                data.bookId++,
                dto.isbn(),
                dto.title(),
                dto.author(),
                dto.year(),
                dto.availableCopies()
        );
        data.books.add(book);
        return toBookDto(book);
    }

    public BookDto findByIsbn(String isbn) {
        Book book = data.books.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("There is no book with isbn like this"));
        return toBookDto(book);
    }

    public List<BookDto> findAvailable() {
        return data.books.stream()
                .filter(book -> book.getAvailableCopies() > 0)
                .map(this::toBookDto)
                .toList();
    }

    public List<BookDto> search(String query) {
        return data.books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase()))
                .map(this::toBookDto)
                .toList();
    }

    private BookDto toBookDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book.getAvailableCopies()
        );
    }
}
