package pd4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public abstract sealed class RentalAsset permits Bike, Motorbike {
    private final int id;
    private final AssetType assetType;
    private BigDecimal basePrice;
    private String name;

    public BigDecimal additionalCharge() {
        return BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return "id: " + id +
                " Nazwa: '" + name + '\'' +
                " Cena wypożyczenia za dzień: " + basePrice.add(additionalCharge()) + "zł " +
                " Typ zasobu: " + assetType;

    }
}

