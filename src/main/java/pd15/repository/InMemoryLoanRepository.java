package pd15.repository;

import lombok.RequiredArgsConstructor;
import pd15.entity.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor(staticName = "initialize")
public class InMemoryLoanRepository implements InMemoryRepository<Loan> {

    private final List<Loan> loans = new ArrayList<>();

    public void save(Loan loan) {
        loans.add(loan);
    }

    public List<Loan> findOverdue() {
        return loans.stream()
                .filter(Loan::isOverdue)
                .toList();
    }

    @Override
    public Optional<Loan> findById(Long id) {
        return Optional.empty();
    }
}
