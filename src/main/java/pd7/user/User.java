package pd7.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class User {
    private final String name;
    private final String email;
    private final String password;
}
