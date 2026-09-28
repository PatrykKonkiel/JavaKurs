package pd5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product implements Entity<String> {
    private final String id;
    private String name;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
