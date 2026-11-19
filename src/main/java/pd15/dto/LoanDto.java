package pd15.dto;

import java.time.LocalDate;

public record LoanDto(Long id,
               Long bookId,
               Long memberId,
               LocalDate borrowedAt,
               LocalDate dueDate,
               LocalDate returnedAt) {
}
