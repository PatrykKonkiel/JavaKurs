package pd15.service;

import lombok.AllArgsConstructor;
import pd15.LibraryData;
import pd15.dto.LoanDto;
import pd15.dto.MemberDto;
import pd15.entity.Loan;
import pd15.entity.Member;
import pd15.exceptions.MemberNotFoundException;

import java.util.List;

@AllArgsConstructor(staticName = "of")
public class MemberService {
    private LibraryData data;

    public MemberDto register(MemberDto dto) {
        Member member = new Member(
                data.memberId++,
                dto.name(),
                dto.email()
        );
        data.members.add(member);
        return toMemberDto(member);
    }

    public MemberDto findById(Long id) {
        Member member = data.members.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new MemberNotFoundException("Member with this id does not exist"));

        return toMemberDto(member);
    }

    public List<LoanDto> getActiveLoans(Long memberId) {
        Member member = data.members.stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst()
                .orElseThrow(() -> new MemberNotFoundException("Member with this id does not exist"));

        return member.getLoans().stream()
                .filter(loan -> loan.getReturnedAt() == null).map(this::toLoanDto).toList();

    }

    private MemberDto toMemberDto(Member member) {
        return new MemberDto(
                member.getId(),
                member.getName(),
                member.getEmail()
        );
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
