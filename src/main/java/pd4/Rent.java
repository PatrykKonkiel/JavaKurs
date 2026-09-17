package pd4;

import java.math.BigDecimal;

public class Rent implements Comparable<Rent> {

    private final RentalAsset rentalAsset;
    private final int dayNumber;
    private RentalStatus status;

    public Rent(RentalAsset rentalAsset, int dayNumber, RentalStatus status) {
        this.rentalAsset = rentalAsset;
        this.dayNumber = dayNumber;
        this.status = status;
    }

    public RentalAsset getRentalAsset() {
        return rentalAsset;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }

    public BigDecimal rentalCost() {
        return (this.rentalAsset.getBasePrice().add(rentalAsset.additionalCharge())).multiply(BigDecimal.valueOf(dayNumber));
    }

    @Override
    public String toString() {
        return "Wypożyczenie " + this.rentalAsset
                + " Ilość dni: " + this.dayNumber
                + " Status wypożyczenia: " + this.status
                + " Łączny koszt usługi: " + this.rentalCost() + " zł";

    }

    @Override
    public int compareTo(Rent o) {
        return this.rentalAsset.getBasePrice().add(rentalAsset.additionalCharge())
                .compareTo(o.rentalAsset.getBasePrice().add(o.rentalAsset.additionalCharge()));

    }
}
