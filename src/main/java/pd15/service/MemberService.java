package pd15.service;

import lombok.AllArgsConstructor;
import pd15.dto.LoanDto;
import pd15.dto.MemberDto;
import pd15.entity.Loan;
import pd15.entity.Member;
import pd15.exceptions.MemberNotFoundException;
import pd15.mapper.LoanMapper;
import pd15.mapper.MemberMapper;
import pd15.repository.InMemoryMemberRepository;

import java.util.List;

@AllArgsConstructor(staticName = "of")
public class MemberService {
    private final InMemoryMemberRepository memberRepository;

    public MemberDto register(MemberDto dto) {
        Member member = new Member(
                dto.name(),
                dto.email()
        );
        memberRepository.register(member);
        return MemberMapper.toDto(member);
    }

    public MemberDto findById(Long id) {
        Member member = memberRepository.findById(id).stream()
                .findFirst()
                .orElseThrow(() -> new MemberNotFoundException(id));

        return MemberMapper.toDto(member);
    }

    public List<LoanDto> getActiveLoans(Long memberId) {
        memberRepository.findById(memberId).stream()
                .findFirst()
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        return memberRepository.getActiveLoans(memberId).stream().map(LoanMapper::toDto).toList();

    }
}
