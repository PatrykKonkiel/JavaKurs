package pd15;

import lombok.NoArgsConstructor;
import pd15.entity.Book;
import pd15.entity.Loan;
import pd15.entity.Member;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(staticName = "inicialize")
public class LibraryData {

    public List<Book> books = new ArrayList<>();
    public List<Member> members = new ArrayList<>();
    public List<Loan> loans = new ArrayList<>();

    public Long bookId = 1L;
    public Long memberId = 1L;
    public Long loanId = 1L;


}
