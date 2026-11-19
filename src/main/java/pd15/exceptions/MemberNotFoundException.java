package pd15.exceptions;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(Long id) {
        super("Member with this id %s does not exist".formatted(id));
    }
}
