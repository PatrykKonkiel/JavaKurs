package pd15.entity;

import lombok.Getter;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

@Getter

public class Loan {
    private Long id;
    private Book book;
    private Member member;
    private LocalDate borrowedAt;
    private LocalDate dueDate;
    private LocalDate returnedAt;

    private static final AtomicLong counter = new AtomicLong(0);

    public Loan(Book book, Member member, LocalDate borrowedAt) {
        this.book = book;
        this.member = member;
        this.borrowedAt = borrowedAt;
        this.dueDate = borrowedAt.plusDays(14);
        this.returnedAt = null;
        id = counter.incrementAndGet();
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
