package database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Michal on 22.03.2019
 */

@Entity
@Table(name = "DISHES")
@Getter
@Setter
@NoArgsConstructor
public class Dish {

    public enum Type {
        MAIN_COURSE,
        SOUP,
        DESSERT,
        SALAD,
        HOT_DRINK,
        COLD_DRINK
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIS_ID")
    private Long id;

    @Column(name = "DIS_NAME", unique = true)
    private String dishName;

    @Column(name = "DIS_TYPE")
    private Type type;

    @ManyToMany(mappedBy = "dishes",fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;

    @ManyToMany(mappedBy = "orderedDishes")
    private List<SingleOrder> singleOrder;

    @Column(name = "DIS_COST")
    private Long costInPennies;

    @Transient
    public Double getCostInZlotys(){
        return (costInPennies / 100.0);
    }

    public Dish(String dishName, Type type, List<Ingredient> ingredients, List<SingleOrder> singleOrder, Long costInPennies) {
        this.dishName = dishName;
        this.type = type;
        this.ingredients = ingredients;
        this.singleOrder = singleOrder;
        this.costInPennies = costInPennies;
    }
}
