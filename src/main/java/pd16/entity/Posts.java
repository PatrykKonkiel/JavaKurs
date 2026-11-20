package pd16.entity;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Posts {
    private int userId;
    private int id;
    private String title;
    private String body;
}
