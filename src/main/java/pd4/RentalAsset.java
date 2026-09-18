package pd4;

import java.math.BigDecimal;

public abstract sealed class RentalAsset permits Bike, Motorbike {
    private final int id;
    private String name;
    private BigDecimal basePrice;
    private final AssetType assetType;

    public RentalAsset(int id, AssetType assetType, BigDecimal basePrice, String name) {
        this.id = id;
        this.assetType = assetType;
        this.basePrice = basePrice;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public AssetType getAssetType() {
        return assetType;
    }

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

