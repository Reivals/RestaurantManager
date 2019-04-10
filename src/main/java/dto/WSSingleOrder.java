package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import database.Dish;
import database.SingleOrder;
import exceptions.ApplicationException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

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

    @JsonProperty("id")
    private Long id;

    public WSSingleOrder fillProperties(SingleOrder singleOrder){
        this.clientFirstName = singleOrder.getClient().getFirstName();
        this.clientLastName = singleOrder.getClient().getSecondName();
        this.tableNumber = singleOrder.getTableNumber();
        this.id = singleOrder.getId();
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
            dish.setCost(d.getCostInZlotys());
            dish.setId(d.getId());
            dishList.add(dish);
        });
        return this;
    }

    public WSSingleOrder(String clientFirstName, String clientLastName, Long tableNumber, List<WSDish> dishList) {
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.tableNumber = tableNumber;
        this.dishList = dishList;
    }

    public WSSingleOrder() {
    }

    public void validateOrder() throws ApplicationException {
        if(StringUtils.isBlank(clientFirstName)){
            throw new ApplicationException("Client first name cannot be blank!");
        } else if(StringUtils.isBlank(clientLastName)){
            throw new ApplicationException("Client last name cannot be blank!");
        } else if(tableNumber == null || tableNumber <= 0 ){
            throw new ApplicationException("Invalid table number passed!");
        } else if(dishList == null || dishList.isEmpty()){
            throw new ApplicationException("There has to be at least one dish in the order!");
        }
    }
}
