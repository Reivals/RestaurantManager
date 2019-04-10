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
@Table(name = "INGREDIENT")
@Getter
@Setter
@NoArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ING_IG")
    private Long id;

    @Column(name = "ING_NAME")
    private String name;

    @Column(name = "ING_CALORIES")
    private Double calories;

    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.EAGER)
    private List<Dish> dishes;


    public Ingredient(String name, Double calories) {
        this.name = name;
        this.calories = calories;
    }
}
