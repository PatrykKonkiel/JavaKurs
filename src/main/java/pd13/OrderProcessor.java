package pd13;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderProcessor {
    private final LaptopStore laptopStore;

    public OrderResult process(Order order) {
        boolean purchase = laptopStore.tryPurchase(order.getQuantity());
        OrderStatus orderStatus = purchase ? OrderStatus.COMPLETED : OrderStatus.DECLINED;
        return new OrderResult(order.getUserName(), order.getQuantity(), orderStatus);
    }
}
