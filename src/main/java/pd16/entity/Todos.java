package pd16.entity;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Todos {
    private int userId;
    private int id;
    private String title;
    private boolean completed;

}
