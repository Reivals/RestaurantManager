package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import database.Ingredient;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Michal on 24.03.2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class WSIngredient {

    @JsonProperty("ingredientName")
    private String ingredientName;

    @JsonProperty("calories")
    private Double calories;

    public WSIngredient fillPropeties(Ingredient i) {
        this.ingredientName=i.getName();
        this.calories=i.getCalories();
        return this;
    }
}
