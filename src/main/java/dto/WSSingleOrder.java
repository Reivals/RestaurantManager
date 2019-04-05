package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import database.SingleOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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

    public WSSingleOrder fillProperties(SingleOrder singleOrder){
        this.clientFirstName = singleOrder.getClient().getFirstName();
        this.clientLastName = singleOrder.getClient().getSecondName();
        this.tableNumber = singleOrder.getTableNumber();
        if(dishList == null){
            dishList = new ArrayList<>();
        }
        singleOrder.getOrderedDishes().stream().forEach( d -> {
            WSDish dish = new WSDish();
            dish.setType(d.getType());
            dish.setName(d.getDishName());
            d.getIngredients().stream().forEach(i -> {
                dish.getIngredients().add(new WSIngredient().fillPropeties(i));
            });
            dishList.add(dish);
        });
        return this;
    }
}
