package pd15.exceptions;

public class LoanLimitExceededException extends RuntimeException{
    public LoanLimitExceededException(Long id) {
        super("Member with id %d has reached loan limit".formatted(id));
    }
}
