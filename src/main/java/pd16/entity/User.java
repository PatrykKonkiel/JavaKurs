package pd16.entity;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

}
