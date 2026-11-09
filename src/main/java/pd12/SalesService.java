package pd12;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SalesService {
    public static Map<String, Map<BigDecimal, BigDecimal>> totalAndAverage(List<Sale> sales) {
        return sales.stream().collect(Collectors.groupingBy(
                Sale::getCategory, Collectors.collectingAndThen(
                        Collectors.toList(), list -> {
                            BigDecimal income = list.stream()
                                    .map(Sale::getRevenue)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                            BigDecimal average = list
                                    .stream()
                                    .map(Sale::getRevenue)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                                    .divide(BigDecimal.valueOf(list.size()), 2, RoundingMode.HALF_UP);
                            return Map.of(income, average);
                        }
                )
        ));
    }

    public static Map<String, BigDecimal> top3Products(List<Sale> sales) {
        return sales.stream().collect(Collectors.groupingBy(
                        Sale::getProductName,
                        Collectors.mapping(
                                Sale::getRevenue,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                        )
                )).entrySet()
                .stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (k, v) -> k, LinkedHashMap::new
                ));
    }

    public static Map<String, BigDecimal> reveneuPerCategory(List<Sale> sales) {
        return sales.stream().collect(Collectors.groupingBy(
                        Sale::getCategory,
                        Collectors.mapping(
                                Sale::getRevenue,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                        )
                )).entrySet()
                .stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (k, v) -> k, LinkedHashMap::new
                ));
    }

    public static LocalDate bestSaleDay(List<Sale> sales) {
        return sales.stream().collect(Collectors.groupingBy(
                        Sale::getDate,
                        Collectors.mapping(
                                Sale::getRevenue,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                        )
                )).entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow()
                .getKey();
    }
}
