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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIS_ID")
    private Long id;

    @Column(name = "DIS_NAME", unique = true)
    private String dishName;

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

}
