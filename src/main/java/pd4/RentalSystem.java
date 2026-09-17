package pd4;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RentalSystem {

    private final List<Rent> rent;

    public RentalSystem() {
        this.rent = new ArrayList<>();
    }

    public void addRent(Rent rent) {
        this.rent.add(rent);
    }

    public BigDecimal totalCost() {
        BigDecimal sum = BigDecimal.ZERO;

        for (Rent rent : this.rent) {
            sum = sum.add(rent.rentalPrice());
        }
        return sum;
    }

    public int countRentalStatusOf(RentalStatus status) {
        int count = 0;
        for (Rent rent : this.rent) {
            if (rent.getStatus() == status) {
                count++;
            }
        }
        return count;
    }

    public List<Rent> getRent() {
        return this.rent.stream().sorted().toList();
    }

    public List<Rent> sortByName() {
        return this.rent.stream().sorted(Comparator.comparing(r -> r.getRentalAsset()
                .getName())).toList();

    }

}
