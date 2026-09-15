package zl5;

public class Electronics extends Product {
    private String warranty;

    public Electronics(String name, double price, String category, String warranty) {
        super(name, price, category);
        this.warranty = warranty;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Gwarancja: " + warranty;

    }
}
