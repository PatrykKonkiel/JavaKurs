package pd9;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        EventProcessor eventProcessor = EventProcessor.inicialize();
        List<Event> events = List.of(
                Event.of("ABC", "Error", "Something gone wrong", EventPriority.LOW),
                Event.of("DEF", "Bomba", "Something gone wrong", EventPriority.LOW),
                Event.of("GHI", "Bomba", "The bomb is bombing", EventPriority.MEDIUM),
                Event.of("JPGa", "Error", "SoMething gone wrong", EventPriority.HIGH),
                Event.of("XDD", "Others", "Dunno what this is", EventPriority.LOW)
        );
        Predicate<Event> onlyErrorTypes = event -> event.getType().equalsIgnoreCase("error");
        Predicate<Event> onlyMediumPriority = event -> event.getPriority() == EventPriority.MEDIUM;
        Predicate<Event> onlyContainsLetterAInName = event -> event.getName().toLowerCase().contains("a");
        Predicate<Event> onlyShortNames = event -> event.getName().length() < 5;
        Predicate<Event> containsSomethingInMessage = event -> event.getMessage().toLowerCase().contains("something");

        Function<Event, String> onlyName = Event::getName;
        Function<Event, String> withEverything = event -> "Name: " + event.getName() + " Type: " + event.getType() + " Message: "
                + event.getMessage() + " Priority: " + event.getPriority();

        List<Predicate<Event>> noFilters = List.of();
        List<Predicate<Event>> errorsAndContainA = List.of(onlyErrorTypes, onlyContainsLetterAInName);
        List<Predicate<Event>> onlyMediumPrio = List.of(onlyMediumPriority);
        List<Predicate<Event>> onlySomethingInMess = List.of(containsSomethingInMessage);

        Consumer<String> print = System.out::println;
        Consumer<String> printWithBracket = s -> System.out.println("[" + s + "]");

        eventProcessor.process(events, noFilters, onlyName, print);
        eventProcessor.process(events, errorsAndContainA, withEverything, print);
        eventProcessor.process(events, onlyMediumPrio, withEverything, printWithBracket);
        eventProcessor.process(events, onlySomethingInMess, withEverything, printWithBracket);


    }
}
