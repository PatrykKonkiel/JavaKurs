package pd15.service;

import lombok.AllArgsConstructor;
import pd15.dto.LoanDto;
import pd15.entity.Book;
import pd15.entity.Loan;
import pd15.entity.Member;
import pd15.exceptions.BookNotFoundException;
import pd15.exceptions.LoanLimitExceededException;
import pd15.exceptions.LoanNotFoundException;
import pd15.exceptions.MemberNotFoundException;
import pd15.mapper.LoanMapper;
import pd15.repository.InMemoryBookRepository;
import pd15.repository.InMemoryLoanRepository;
import pd15.repository.InMemoryMemberRepository;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor(staticName = "of")
public class LoanService {
    private final InMemoryLoanRepository loanRepository;
    private final InMemoryMemberRepository memberRepository;
    private final InMemoryBookRepository bookRepository;

    public LoanDto borrow(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId).stream()
                .findFirst()
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        if (!member.canBorrow()) {
            throw new LoanLimitExceededException(memberId);
        }
        Book book = bookRepository.findById(bookId).stream()
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(bookId));
        book.borrow();
        Loan loan = new Loan(
                book,
                member,
                LocalDate.now()
        );
        member.addLoan(loan);
        loanRepository.save(loan);
        return LoanMapper.toDto(loan);
    }

    public LoanDto returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId).stream()
                .findFirst().orElseThrow(() -> new LoanNotFoundException(loanId));

        if (loan.getReturnedAt() != null) {
            throw new IllegalArgumentException("Loan was already returned");
        } else {
            loan.returnedBook();
            loan.getBook().returnBack();
        }
        return LoanMapper.toDto(loan);
    }

    public List<LoanDto> findOverdue() {
        return loanRepository.findOverdue().stream()
                .map(LoanMapper::toDto)
                .toList();
    }

}
