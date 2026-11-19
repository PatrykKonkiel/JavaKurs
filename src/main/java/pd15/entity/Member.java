package pd15.entity;

import lombok.Getter;
import pd15.exceptions.LoanLimitExceededException;
import java.util.ArrayList;
import java.util.List;
@Getter
public class Member {
    private Long id;
    private String name;
    private String email;
    private List<Loan> loans = new ArrayList<>();

    public Member(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void canBorrow() {
        if (loans.size() > 3) {
            throw new LoanLimitExceededException("Member exceeded limit of borrowed books");
        }
    }

    public void addLoan(Loan loan){
        loans.add(loan);
    }
}
