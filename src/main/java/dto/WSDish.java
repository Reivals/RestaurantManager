package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import database.Dish;
import database.Ingredient;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal on 24.03.2019
 */

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WSDish {

    @JsonProperty("dishName")
    private String name;

    @JsonProperty("ingredients")
    private List<WSIngredient> ingredients = new ArrayList<>();


    public WSDish fillProperties(Dish dish){
        this.name = dish.getDishName();
        dish.getIngredients().stream().forEach(i -> {
            ingredients.add(new WSIngredient().fillPropeties(i));
        });
        return this;
    }
}
