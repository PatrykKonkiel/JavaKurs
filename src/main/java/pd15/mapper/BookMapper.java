package pd15.mapper;

import pd15.dto.BookDto;
import pd15.entity.Book;

public class BookMapper {
    public static BookDto toDto(Book book) {
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
