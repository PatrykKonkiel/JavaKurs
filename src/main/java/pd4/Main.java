package pd4;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        RentalAsset bike1 = new Bike(1, AssetType.BIKE, BigDecimal.valueOf(10), "SkładakDelux", true);
        RentalAsset bike2 = new Bike(12, AssetType.BIKE, BigDecimal.valueOf(5), "Jakiś rower", false);
        RentalAsset bike3 = new Bike(15, AssetType.BIKE, BigDecimal.valueOf(15), "SkładakKlasyczny", false);
        RentalAsset motorbike1 = new Motorbike(6, AssetType.MOTORBIKE, BigDecimal.valueOf(8), "ElMotoro", "Honda");
        RentalAsset motorbike2 = new Motorbike(61, AssetType.MOTORBIKE, BigDecimal.valueOf(4), "BleBle", "Cosiek");
        RentalAsset motorbike3 = new Motorbike(65, AssetType.MOTORBIKE, BigDecimal.valueOf(11), "Wariacik", "Honda");
        Rent rent1 = new Rent(bike1, 10, RentalStatus.ACTIVE);
        Rent rent2 = new Rent(bike2, 5, RentalStatus.RETURNED);
        Rent rent3 = new Rent(bike3, 8, RentalStatus.RETURNED);
        Rent rent4 = new Rent(motorbike1, 8, RentalStatus.ACTIVE);
        Rent rent5 = new Rent(motorbike2, 2, RentalStatus.RETURNED);
        Rent rent6 = new Rent(motorbike3, 5, RentalStatus.ACTIVE);
        RentalSystem system = new RentalSystem();
        system.addRent(rent1);
        system.addRent(rent2);
        system.addRent(rent3);
        system.addRent(rent4);
        system.addRent(rent5);
        system.addRent(rent6);


        System.out.println("Wypożyczenia: ");
        for(Rent rent : system.getRent()){
            System.out.println(rent);
        }

        System.out.println("Łączny koszt: " +system.totalCost() + " zł");

        System.out.println("Ilość aktywnych wypożyczeń: "+ system.countRentalStatusOf(RentalStatus.ACTIVE));

        System.out.println("Posortowanie po cenie: ");
        for(Rent rent: system.sortByName()){
            System.out.println(rent);
        }

    }
}
