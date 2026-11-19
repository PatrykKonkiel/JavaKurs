package pd15;

import pd15.dto.BookDto;
import pd15.dto.LoanDto;
import pd15.dto.MemberDto;
import pd15.service.BookService;
import pd15.service.LoanService;
import pd15.service.MemberService;

public class Main {
    public static void main(String[] args) {
        LibraryData data = LibraryData.inicialize();
        MemberService memberService = MemberService.of(data);
        BookService bookService = BookService.of(data);
        LoanService loanService = LoanService.of(data);

        bookService.addBook(new BookDto(null, "55555", "ABC", "sdddd", 1999, 5));
        memberService.register(new MemberDto(null, "Janek", "sdasdasd"));
        LoanDto borrow = loanService.borrow(1L, 1L);
        System.out.println(borrow.id());
        System.out.println(borrow.bookId());
        System.out.println(borrow.memberId());
        System.out.println(borrow.borrowedAt());
        System.out.println(borrow.dueDate());
        System.out.println(borrow.returnedAt());


    }
}
