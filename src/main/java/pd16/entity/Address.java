package pd16.entity;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
}
