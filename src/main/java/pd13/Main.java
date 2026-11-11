package pd13;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        LaptopStore store = new LaptopStore(10);
        OrderProcessor orderProcessor = new OrderProcessor(store);
        OrderService orderService = new OrderService(orderProcessor);
        List<Order> orderList = List.of(
                new Order("Jacek", 5),
                new Order("Marek", 4),
                new Order("Cwel", 3),
                new Order("Kaban", 1)
        );
        List<OrderResult> orderResults = orderService.processOrders(orderList);
            orderResults.stream()
                    .filter(order -> order.getOrderStatus() == OrderStatus.COMPLETED)
                    .forEach(result -> System.out.println(
                            result.getUserName() + " bought " + result.getQuantity() + " laptops"));
            orderResults.stream().filter(order -> order.getOrderStatus() == OrderStatus.DECLINED)
                    .forEach(result -> System.out.println(result.getUserName() + " transaction has been declined"));
            System.out.println("Laptops left " + store.getAvailableQuantity());
            System.out.println("Laptops sold " + store.getSoldQuantity());
    }
}
