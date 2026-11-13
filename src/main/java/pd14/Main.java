package pd14;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate localDate = DateCalculator.nextPayday(LocalDate.of(2026,12,1));
        System.out.println(localDate);
        long l = DateCalculator.businessDaysBetween(LocalDate.now(), LocalDate.of(2026, 11, 19));
        System.out.println(l);
    }
}
