package zl6;

public class Product {
    private final String name;

    public Product(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Produkt: " + name;
    }
}
