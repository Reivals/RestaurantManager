package database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
    },
    fetch = FetchType.EAGER)
    @JoinTable(name = "dish_orders",
            joinColumns = @JoinColumn(name = "SOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "DIS_ID")
    )
    private List<Dish> orderedDishes;

    @Column(name = "SOR_TABLE_NUMBER")
    private Long tableNumber;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            optional = false)
    @JoinColumn(name = "SOR_CLIENT_ID")
    private Client client;

    @Temporal(TemporalType.DATE)
    @Column(name = "SOR_CREATION_DATE")
    private Date date;

    @PrePersist
    private void fillCreationDate(){
        this.date = new Date();
    }

    public void SingleOrder(){
        this.orderedDishes = new ArrayList<>();
    }

    public void addDish(Dish dish){
        this.orderedDishes.add(dish);
        dish.getSingleOrder().add(this);

    }

    public void removeDish(Dish dish){
        this.orderedDishes.remove(dish);
        dish.getSingleOrder().remove(this);
    }

    public void removeAllDishesFromOrder(){
        this.orderedDishes.clear();
    }

    public void removeAllOccurrencesOf(Dish dish) {
        while (this.orderedDishes.remove(dish)) {
            dish.getSingleOrder().remove(this);
        }
    }


}
