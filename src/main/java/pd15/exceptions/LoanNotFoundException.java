package pd15.exceptions;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(Long id) {
        super("Loan with this id %s does not exist".formatted(id));
    }
}
