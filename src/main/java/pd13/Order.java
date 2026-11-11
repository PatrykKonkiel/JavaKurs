package pd13;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public class Order {
    private final String userName;
    private final int quantity;
}
