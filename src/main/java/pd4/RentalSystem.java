package pd4;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class RentalSystem {

    private final List<Rent> rent;

    private RentalSystem() {
        this.rent = new ArrayList<>();
    }

    public static RentalSystem initialize() {
        return new RentalSystem();
    }

    public void addRent(Rent rent) {
        this.rent.add(rent);
    }

    public BigDecimal totalCost() {
        BigDecimal sum = BigDecimal.ZERO;

        for (Rent rent : this.rent) {
            sum = sum.add(rent.rentalCost());
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
        return this.rent.stream().sorted(Comparator.comparing(rent -> rent.getRentalAsset()
                .getName())).toList();

    }


}
