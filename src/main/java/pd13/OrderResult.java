package pd13;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderResult {
    private final String userName;
    private final int quantity;
    private final OrderStatus orderStatus;

}
