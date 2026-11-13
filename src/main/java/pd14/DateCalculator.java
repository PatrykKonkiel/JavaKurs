package pd14;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.stream.Stream;

public class DateCalculator {
    //    age(LocalDate birthDate) → int (wiek w latach) +
//    nextPayday(LocalDate from) → LocalDate (ostatni dzień roboczy miesiąca) +
//    businessDaysBetween(LocalDate from, LocalDate to) → long (dni roboczych) +
//    convertTimezone(ZonedDateTime dt, String targetZone) → ZonedDateTime+
//    formatForLocale(LocalDateTime dt, String languageTag) → String (dd MMMM yyyy)
//    isValidDate(String input, String pattern) → boolean (safe parse)
    public static int age(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public static LocalDate nextPayday(LocalDate from) {
        LocalDate localDate = from.withDayOfMonth(from.lengthOfMonth());
        if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            localDate = localDate.minusDays(1);
        } else if (localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            localDate = localDate.minusDays(2);
        }
        return localDate;
    }

    public static long businessDaysBetween(LocalDate from, LocalDate to) {
        return from.datesUntil(to)
                .filter(day -> day.getDayOfWeek() != DayOfWeek.SUNDAY && day.getDayOfWeek() != DayOfWeek.SATURDAY).count();
    }

    public static ZonedDateTime convertTimezone(ZonedDateTime dt, String targetZone) {
        return dt.withZoneSameInstant(ZoneId.of(targetZone));
    }

    public static String formatForLocale(LocalDateTime dt, String languageTag){
        return dt.format(DateTimeFormatter.ofPattern(languageTag, Locale.forLanguageTag("pl")));
    }

    public static boolean isValidDate(String input, String pattern){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern,Locale.ROOT).withResolverStyle(ResolverStyle.STRICT);
            formatter.parse(input);
            return true;
        }catch (DateTimeException e){
            return false;
        }
    }
}
