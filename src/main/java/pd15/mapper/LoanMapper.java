package pd15.mapper;

import pd15.dto.LoanDto;
import pd15.entity.Loan;

public class LoanMapper {
    public static LoanDto toDto(Loan loan) {
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
