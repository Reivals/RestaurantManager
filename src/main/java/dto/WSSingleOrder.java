package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Michal on 03.04.2019
 */
@Getter
@Setter
public class WSSingleOrder {

    @JsonProperty("clientFirstName")
    private String clientFirstName;

    @JsonProperty("clientLastName")
    private String clientLastName;

    @JsonProperty("tableNumber")
    private Long tableNumber;

    @JsonProperty("dishList")
    private List<WSDish> dishList;
}
