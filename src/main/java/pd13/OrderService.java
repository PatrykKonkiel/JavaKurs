package pd13;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class OrderService {
    private final OrderProcessor orderProcessor;

    public List<OrderResult> processOrders(List<Order> orders) {
        List<CompletableFuture<OrderResult>> list = orders.stream()
                .map(order -> CompletableFuture.supplyAsync(() -> orderProcessor.process(order)
                ))
                .toList();
        return list.stream().map(CompletableFuture::join).toList();
    }
}
