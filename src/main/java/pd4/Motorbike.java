package pd4;

import java.math.BigDecimal;

public final class Motorbike extends RentalAsset {

    private String brand;

    public Motorbike(int id, AssetType assetType, BigDecimal basePrice, String name, String brand) {
        super(id, assetType, basePrice, name);
        this.brand = brand;
    }

    @Override
    public BigDecimal additionalCharge() {
        return super.additionalCharge();
    }

    @Override
    public String toString() {
        return super.toString() + " Marka motoru: " + brand;
    }
}
