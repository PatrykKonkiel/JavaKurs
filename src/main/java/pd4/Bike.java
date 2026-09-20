package pd4;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

public final class Bike extends RentalAsset {

    private boolean electric;

    public Bike(int id, AssetType assetType, BigDecimal basePrice, String name, boolean electric) {
        super(id, assetType, basePrice, name);
        this.electric = electric;
    }


    @Override
    public BigDecimal additionalCharge() {
        if (electric) {
            return BigDecimal.valueOf(5);
        } else {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Rower elekryczny: " + ((electric) ? "Tak" : "Nie");
    }
}
