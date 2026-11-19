package pd15.repository;

import lombok.RequiredArgsConstructor;
import pd15.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor(staticName = "initialize")
public class InMemoryBookRepository implements InMemoryRepository<Book> {
    private final List<Book> books = new ArrayList<>();

    public void save(Book book) {
        books.add(book);
    }

    public Optional<Book> findByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    public List<Book> findAvailable() {
        return books.stream()
                .filter(book -> book.getAvailableCopies() > 0)
                .toList();
    }

    public List<Book> search(String query) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase(Locale.ROOT)
                        .contains(query.toLowerCase(Locale.ROOT)))
                .toList();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }
}
