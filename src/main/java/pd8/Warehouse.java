package pd8;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
@NoArgsConstructor(staticName = "inicialize")
public class Warehouse {
    private Map<Long, Product> inventory = new HashMap<>();
    private Map<String, Set<Product>> byCategory = new HashMap<>();
    private PriorityQueue<Product> lowStock = new PriorityQueue<>();
    private static final int LOW_STOCK = 5;

    public void addProduct(Product product) {
        if (inventory.containsKey(product.getId())) {
            throw new IllegalArgumentException("Product with this id already exist");
        }
        inventory.put(product.getId(), product);
        byCategory.putIfAbsent(product.getCategory(), new HashSet<>());
        byCategory.get(product.getCategory()).add(product);
        if (product.getQuantity() < LOW_STOCK) {
            lowStock.add(product);
        }
    }

    public void removeProduct(Long id) {
        Product product = inventory.remove(id);
        if (product == null) {
            throw new IllegalArgumentException("There is no product with this id");
        }
        Set<Product> products = byCategory.get(product.getCategory());
        if (products != null) {
            products.remove(product);
        } else {
            throw new IllegalArgumentException("There is no product with this id");
        }
        if (products.isEmpty()) {
            byCategory.remove(product.getCategory());
        }
        lowStock.remove(product);
    }

    public void updateQuantity(Long id, int newQuantity) {
        Product product = inventory.get(id);

        if (product == null) {
            throw new IllegalArgumentException("There is no product with this id");
        }
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        product.setQuantity(newQuantity);
        lowStock.remove(product);
        if (newQuantity < LOW_STOCK) {
            lowStock.add(product);
        }
    }

    public List<Product> findByCategory(String category) {
        if (category == null) {
            throw new IllegalArgumentException("There is no such category");
        }
        return byCategory.get(category)
                .stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .toList();
    }

    public List<Product> getLowStockReport() {
        return lowStock.stream()
                .sorted(Comparator.comparing(Product::getQuantity))
                .toList();
    }

    public Map<String, DoubleSummaryStatistics> getCategoryStats() {
        Map<String, DoubleSummaryStatistics> stats = new HashMap<>();
        for (Product value : inventory.values()) {
            String productCategory = value.getCategory();
            if (!stats.containsKey(productCategory)) {
                stats.put(productCategory, new DoubleSummaryStatistics());
            }
            stats.get(productCategory).accept(value.getPrice().doubleValue());
        }
        return stats;
    }

    public TreeMap<BigDecimal, List<Product>> exportSortedPrice() {
        return inventory.values().stream()
                .collect(Collectors.groupingBy(
                        Product::getPrice,
                        TreeMap::new,
                        Collectors.toList()
                ));
    }
}
