package database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal on 22.03.2019
 */
@Entity
@Table(name = "INGREDIENT")
@Getter
@Setter
@NoArgsConstructor
public class Ingredient {

    public enum Type{
        VEGETABLE,
        MEAT,
        FRUIT,
        //TODO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ING_IG")
    private Long id;

    @Column(name = "ING_NAME")
    private String name;

    @Column(name = "ING_CALORIES")
    private Double calories;

    @Column(name = "ING_TYPE")
    private Type type;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "dish_ingredients",
            joinColumns = @JoinColumn(name = "ING_IG"),
            inverseJoinColumns = @JoinColumn(name = "DIS_ID")
    )
    private List<Dish> dishes;


    public Ingredient(String name, Double calories, Type type) {
        this.name = name;
        this.calories = calories;
        this.type = type;
    }
}
