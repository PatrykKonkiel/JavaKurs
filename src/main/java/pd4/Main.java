package pd4;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<RentalAsset> assets = List.of(
                new Bike(1, AssetType.BIKE, BigDecimal.valueOf(10), "SkładakDelux", true),
                new Bike(12, AssetType.BIKE, BigDecimal.valueOf(5), "Jakiś rower", false),
                new Bike(15, AssetType.BIKE, BigDecimal.valueOf(15), "SkładakKlasyczny", false),
                new Motorbike(6, AssetType.MOTORBIKE, BigDecimal.valueOf(8), "ElMotoro", "Honda"),
                new Motorbike(61, AssetType.MOTORBIKE, BigDecimal.valueOf(4), "BleBle", "Cosiek"),
                new Motorbike(65, AssetType.MOTORBIKE, BigDecimal.valueOf(11), "Wariacik", "Honda"));

        List<Rent> rents = List.of(
                new Rent(assets.get(0), 10, RentalStatus.ACTIVE),
                new Rent(assets.get(1), 5, RentalStatus.RETURNED),
                new Rent(assets.get(2), 8, RentalStatus.RETURNED),
                new Rent(assets.get(3), 8, RentalStatus.ACTIVE),
                new Rent(assets.get(4), 2, RentalStatus.RETURNED),
                new Rent(assets.get(5), 5, RentalStatus.ACTIVE));

        RentalSystem system = RentalSystem.inicialize();
        rents.forEach(system::addRent);


        System.out.println("Wypożyczenia: ");
        for (Rent rent : system.getRent()) {
            System.out.println(rent);
        }

        System.out.println("Łączny koszt: " + system.totalCost() + " zł");

        System.out.println("Ilość aktywnych wypożyczeń: " + system.countRentalStatusOf(RentalStatus.ACTIVE));

        System.out.println("Posortowanie po nazwie: ");
        for (Rent rent : system.sortByName()) {
            System.out.println(rent);
        }

    }
}
