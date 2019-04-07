package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import database.Dish;
import database.Ingredient;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

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

    @JsonProperty("type")
    private Dish.Type type;

    @JsonProperty("cost")
    private Double cost;

    @JsonProperty("id")
    private Long id;

    public WSDish fillProperties(Dish dish) {
        this.name = dish.getDishName();
        this.cost = dish.getCostInZlotys();
        dish.getIngredients().stream().forEach(i -> {
            ingredients.add(new WSIngredient().fillPropeties(i));
        });
        this.type = dish.getType();
        return this;
    }

    public boolean validateWSDish(){
        if(StringUtils.isBlank(name)){
            return false;
        } else if(ingredients == null || ingredients.isEmpty()){
            return false;
        } else if(type == null){
            return false;
        } else if(cost == null){
            return false;
        }
        return true;
    }
}
