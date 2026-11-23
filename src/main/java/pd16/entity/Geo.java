package pd16.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geo {
    @JsonProperty("lat")
    private String latitude;
    @JsonProperty("lng")
    private String longitude;
}
