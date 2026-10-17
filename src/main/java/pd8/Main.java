package pd8;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {

        Warehouse warehouse = Warehouse.inicialize();
        warehouse.addProduct(Product.of(67L, "Telewizor", "AGD", BigDecimal.valueOf(123), 4));
        warehouse.addProduct(Product.of(55L, "Komputer", "AGD", BigDecimal.valueOf(9110), 6));
        warehouse.addProduct(Product.of(45L, "Kubek", "Inne", BigDecimal.valueOf(5), 99));
        warehouse.addProduct(Product.of(85L, "Telefon", "AGD", BigDecimal.valueOf(8521), 2));
        warehouse.addProduct(Product.of(11L, "Poduszka", "Inne", BigDecimal.valueOf(55), 85));
        warehouse.addProduct(Product.of(77L, "Aszwaganda", "Lek", BigDecimal.valueOf(23), 22));

        System.out.println("Search by category:");
        warehouse.findByCategory("Inne")
                .forEach(System.out::println);
        System.out.println("Low Stock:");
        warehouse.getLowStockReport()
                .forEach(System.out::println);
        warehouse.getCategoryStats()
                .forEach((category, stats) -> {
                    System.out.println("Category " + category);
                    System.out.println("Product quantity " + stats.getCount());
                    BigDecimal roundedAverage = BigDecimal.valueOf(stats.getAverage()).setScale(2, RoundingMode.HALF_UP);
                    System.out.println("Average price " + roundedAverage);
                    System.out.println("Minimal price " + stats.getMin());
                    System.out.println("Maximum price " + stats.getMax());
                });
        warehouse.exportSortedPrice()
                .forEach((price, products) -> {
                    System.out.println("Price: " + price + " pln");
                    products.forEach(System.out::println);
                });
        warehouse.removeProduct(45L);
        System.out.println("Updated category Inne");
        warehouse.findByCategory("Inne")
                .forEach(System.out::println);
        warehouse.updateQuantity(11L, 3);
        System.out.println("Updated low stock");
        warehouse.getLowStockReport()
                .forEach(System.out::println);

    }
}
