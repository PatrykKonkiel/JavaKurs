package pd9;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor(staticName = "of")
public class Event {
   private final String name;
   private final String type;
   private final String message;
   private final EventPriority priority;
}
