package pd16;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pd16.entity.Posts;
import pd16.entity.Todos;
import pd16.entity.User;
import pd16.entity.Weather;

import java.util.List;
@ToString
@Getter
@RequiredArgsConstructor
public class UserReport  {
    private final User user;
    private final List<Posts> postsList;
    private final List<Todos> todosList;
    private final Weather weather;



}
