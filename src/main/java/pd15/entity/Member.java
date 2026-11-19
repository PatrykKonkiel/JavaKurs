package pd15.entity;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Getter
public class Member {
    private Long id;
    private String name;
    private String email;
    private List<Loan> loans = new ArrayList<>();

    private static final AtomicLong counter = new AtomicLong(0);

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
        id = counter.incrementAndGet();
    }

    public boolean canBorrow() {
        return loans.size() <= 3;
    }

    public void addLoan(Loan loan){
        loans.add(loan);
    }
}
