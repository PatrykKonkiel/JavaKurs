package pd10;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
@ToString
@Getter
public class Product {
    private final long id;
    private final String name;
    private final String category;
    private final BigDecimal price;
    private final int stock;
    private final int sold;
    private final double rating;

    private static final Set<Long> usedId = new HashSet<>();

    public Product(long id, String name, String category, BigDecimal price, int stock, int sold, double rating) {
        if (rating > 5 || rating < 0) {
            throw new IllegalArgumentException("The rating need to be with 0-5 limit");
        }
        if (usedId.contains(id)) {
            throw new IllegalArgumentException("There is already product with this id");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (sold < 0) {
            throw new IllegalArgumentException("Sold amount cannot be negative value");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Sold amount cannot be negative value");
        }
        usedId.add(id);
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.sold = sold;
        this.rating = rating;
    }

    public BigDecimal getRevenue() {
        return price.multiply(BigDecimal.valueOf(sold));
    }
}

