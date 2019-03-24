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
@Table(name = "SINGLE_ORDER")
@Getter
@Setter
@NoArgsConstructor
public class SingleOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SOR_ID")
    private Long id;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "dish_orders",
            joinColumns = @JoinColumn(name = "SOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "DIS_ID")
    )
    private List<Dish> orderedDishes;

    @Column(name = "SOR_TABLE_NUMBER")
    private Long tableNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOR_SINGLE_ORDER", referencedColumnName = "singleOrder")
    private Client client;

/*    public void addDish(Dish dish){
        orderedDishes.add(dish);
        dish.setSingleOrder(this);
    }

    public void removeDish(Dish dish){
        orderedDishes.remove(dish);
        dish.setSingleOrder(null);
    }*/


}
