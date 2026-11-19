package pd15.service;

import lombok.AllArgsConstructor;
import pd15.dto.BookDto;
import pd15.entity.Book;
import pd15.exceptions.BookNotFoundException;
import pd15.mapper.BookMapper;
import pd15.repository.InMemoryBookRepository;

import java.util.List;

@AllArgsConstructor(staticName = "of")
public class BookService {
    private final InMemoryBookRepository bookRepository;

    public BookDto addBook(BookDto dto) {
        Book book = new Book(
                dto.isbn(),
                dto.title(),
                dto.author(),
                dto.year(),
                dto.availableCopies()
        );
        bookRepository.save(book);
        return BookMapper.toDto(book);
    }

    public BookDto findByIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("Isbn cannot be null or blank");
        }
        return bookRepository.findByIsbn(isbn)
                .map(BookMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("This isbn does not exist"));
    }

    public List<BookDto> findAvailable() {
        return bookRepository.findAvailable().stream().map(BookMapper::toDto).toList();
    }

    public List<BookDto> search(String query) {
        if (query == null || query.isBlank()) {
            throw new IllegalArgumentException("query cannot be null or blank");
        }
        return bookRepository.search(query).stream().map(BookMapper::toDto).toList();
    }

}
