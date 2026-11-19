package pd15.repository;

import lombok.RequiredArgsConstructor;
import pd15.BookStatus;
import pd15.entity.Loan;
import pd15.entity.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor(staticName = "initialize")
public class InMemoryMemberRepository implements InMemoryRepository<Member> {

    private final List<Member> members = new ArrayList<>();

    public void register(Member member) {
        members.add(member);
    }

    public List<Member> memberList() {
        return List.copyOf(members);
    }

    public List<Loan> getActiveLoans(Long memberId) {
        return members.stream()
                .filter(member -> member.getId().equals(memberId))
                .flatMap(member -> member.getLoans().stream())
                .filter(loan -> loan.getBook().getBookStatus() == BookStatus.LOANED)
                .toList();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return members.stream()
                .filter(member -> member.getId().equals(id))
                .findFirst();
    }
}
