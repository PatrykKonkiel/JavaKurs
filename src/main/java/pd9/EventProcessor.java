package pd9;

import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@NoArgsConstructor(staticName = "inicialize")
public class EventProcessor {
    public void process(List<Event> events, List<Predicate<Event>> filters,
                        Function<Event, String> formatter, Consumer<String> output) {
        events.forEach(event -> {
            boolean check = true;
            for (Predicate<Event> filter : filters)
                if (!filter.test(event)) {
                    check = false;
                }
            if(check) {
                String text = formatter.apply(event);
                output.accept(text);
            }
        });
    }
}
