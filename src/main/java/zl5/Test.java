package zl5;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Electronics("Telewizor", 2499, "AGD", "12 months"));
        products.add(new Electronics("Komputer", 2800, "AGD", "6 months"));
        products.add(new FoodProduct("Banan", 16, "Żywność", "12.07.2026"));
        products.add(new FoodProduct("Jabłko", 5, "Żywność", "12.05.2026"));

        for (Product product : products) {
            System.out.println(product.getDescription());

        }
    }

}
