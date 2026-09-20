package pd4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@AllArgsConstructor
@Setter
@Getter
public class Rent implements Comparable<Rent> {

    private final RentalAsset rentalAsset;
    private final int dayNumber;
    private RentalStatus status;

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
