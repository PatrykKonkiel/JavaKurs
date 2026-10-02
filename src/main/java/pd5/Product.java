package pd5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Product implements Indentifiable<String> {
    private final String id;
    private String name;
}
