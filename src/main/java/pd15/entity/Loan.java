package pd15.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
@Getter

public class Loan {
    private Long id;
    private Book book;
    private Member member;
    private LocalDate borrowedAt;
    private LocalDate dueDate;
    private LocalDate returnedAt;

    public Loan(Long id, Book book, Member member, LocalDate borrowedAt, LocalDate dueDate) {
        this.id = id;
        this.book = book;
        this.member = member;
        this.borrowedAt = borrowedAt;
        this.dueDate = dueDate;
        this.returnedAt = null;
    }

    public boolean isOverdue() {
        return returnedAt == null && dueDate.isBefore(LocalDate.now());
    }

    public void returnedBook() {
        if (returnedAt != null) {
            throw new IllegalStateException("Book was already returned");
        }
        returnedAt = LocalDate.now();
    }
}
