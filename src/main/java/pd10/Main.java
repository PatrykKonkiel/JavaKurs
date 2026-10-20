package pd10;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product(1L, "Telewizor", "Elektronika", BigDecimal.valueOf(500), 5, 10, 3.5),
                new Product(2L, "Komputer", "Elektronika", BigDecimal.valueOf(120), 8, 2, 2.5),
                new Product(3L, "Pralka", "AGD", BigDecimal.valueOf(350), 11, 5, 5),
                new Product(4L, "Telefon", "Elektronika", BigDecimal.valueOf(230), 33, 9, 2.9),
                new Product(5L, "Adidaski", "Ciuchy", BigDecimal.valueOf(133), 52, 21, 4.3),
                new Product(6L, "Bluza", "Ciuchy", BigDecimal.valueOf(9999), 3, 35, 3.9),
                new Product(7L, "Koszula", "Ciuchy", BigDecimal.valueOf(111), 4, 25, 4),
                new Product(8L, "Bleble", "Inne", BigDecimal.valueOf(222), 5, 68, 4),
                new Product(9L, "Blublue", "Inne", BigDecimal.valueOf(666), 1, 5, 5),
                new Product(10L, "Blibli", "Inne", BigDecimal.valueOf(444), 6, 1, 5)
        );
        System.out.println("Top 5 products by revenue:");
        ProductSorter.top5ByRevenue(products);
        System.out.println();
        System.out.println("Category with the highest avg rating:");
        ProductSorter.categoryWithHighestRating(products);
        System.out.println();
        System.out.println("Products with stock less than 5:");
        ProductSorter.productWithLowStock(products);
        System.out.println();
        System.out.println("Product amount by category:");
        ProductSorter.productAmountByCategory(products);
        System.out.println();
        System.out.println("Total income and avg price by category:");
        ProductSorter.totalIncomeAndAvgPrice(products);
        System.out.println();
        System.out.println("Products with rating greater than 4.5 and price lower than 500:");
        ProductSorter.highRatingLowPrice(products);
        System.out.println();
        System.out.println("Product with highest price:");
        ProductSorter.highestPriceProduct(products);
        System.out.println();
        System.out.println("Premium and standard products:");
        ProductSorter.premiumAndStandardProducts(products);
        System.out.println();
        System.out.println("Report");
        ProductSorter.printReport(products);

    }
}
