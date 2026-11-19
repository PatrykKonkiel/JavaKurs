package pd15.service;

import lombok.AllArgsConstructor;
import pd15.LibraryData;
import pd15.dto.LoanDto;
import pd15.entity.Book;
import pd15.entity.Loan;
import pd15.entity.Member;
import pd15.exceptions.BookNotFoundException;
import pd15.exceptions.LoanNotFoundException;
import pd15.exceptions.MemberNotFoundException;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor(staticName = "of")
public class LoanService {
    private LibraryData data;

    public LoanDto borrow(Long memberId, Long bookId) {
        Member member = data.members.stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElseThrow(() -> new MemberNotFoundException("Member with this id does not exist"));
        member.canBorrow();
        Book book = data.books.stream()
                .filter(b -> b.getId().equals(bookId))
                .findFirst().orElseThrow(() -> new BookNotFoundException("Book with this id does not exist"));
        book.borrow();
        Loan loan = new Loan(
                data.loanId++,
                book,
                member,
                LocalDate.now(),
                LocalDate.now().plusDays(14)
        );
        member.addLoan(loan);
        data.loans.add(loan);
        return toLoanDto(loan);
    }

    public LoanDto returnBook(Long loanId) {
        Loan loan = data.loans.stream().filter(l -> l.getId().equals(loanId))
                .findFirst().orElseThrow(() -> new LoanNotFoundException("Loan with this id does not exist"));
        loan.returnedBook();
        loan.getBook().returnBack();

        return toLoanDto(loan);
    }

    public List<LoanDto> findOverdue() {
        return data.loans.stream()
                .filter(Loan::isOverdue)
                .map(this::toLoanDto)
                .toList();
    }

    private LoanDto toLoanDto(Loan loan) {
        return new LoanDto(
                loan.getId(),
                loan.getBook().getId(),
                loan.getMember().getId(),
                loan.getBorrowedAt(),
                loan.getDueDate(),
                loan.getReturnedAt()
        );
    }


}
