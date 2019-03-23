package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Michal on 23.03.2019
 */
@Getter
@Setter
public class IngredientType {

    @JsonProperty
    private String name;

    public IngredientType(String name) {
        this.name = name;
    }
}
