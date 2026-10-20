package pd10;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class ProductSorter {
    public static void top5ByRevenue(List<Product> products) {
        products.stream()
                .sorted(Comparator.comparing(Product::getRevenue).reversed())
                .limit(5)
                .forEach(System.out::println);
    }

    public static void categoryWithHighestRating(List<Product> products) {
        products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getRating)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(System.out::println);
    }

    public static void productWithLowStock(List<Product> products) {
        products.stream()
                .filter(product -> product.getStock() < 5)
                .sorted(Comparator.comparing(Product::getStock))
                .forEach(System.out::println);
    }

    public static void productAmountByCategory(List<Product> products) {
        products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void totalIncomeAndAvgPrice(List<Product> products) {
        products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.collectingAndThen(
                        Collectors.toList(), list -> {
                            BigDecimal income = list.stream()
                                    .map(Product::getRevenue)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                            BigDecimal avg = list.stream()
                                    .map(Product::getPrice)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                                    .divide(BigDecimal.valueOf(list.size()), 2, RoundingMode.HALF_UP);
                            return Map.of("Income: ", income, "Average price: ", avg);
                        }
                ))).entrySet().forEach(System.out::println);
    }

    public static void highRatingLowPrice(List<Product> products) {
        products.stream()
                .filter(product -> product.getRating() > 4.5)
                .filter(product -> product.getPrice().compareTo(BigDecimal.valueOf(500)) < 0)
                .sorted(Comparator.comparing(Product::getPrice))
                .forEach(System.out::println);

    }

    public static void highestPriceProduct(List<Product> products) {
        products.stream()
                .max(Comparator.comparing(Product::getPrice))
                .ifPresent(System.out::println);
    }

    public static void premiumAndStandardProducts(List<Product> products) {
        products.stream()
                .collect(Collectors.partitioningBy(
                        product -> product.getPrice().compareTo(BigDecimal.valueOf(1000)) > 0)
                )
                .forEach((key, value) -> {
                    String group = key ? "Premium" : "Standard" + " products";
                    System.out.println(group + " products");
                    value.forEach(System.out::println);
                    System.out.println(group + " count " + value.size());
                });

    }

    public static void printReport(List<Product> products) {
        products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Product::getRevenue)),
                                Optional::orElseThrow
                        )
                ))
                .forEach((category, product) -> System.out.println("Best product in " + category + ": " + product));

    }

}
