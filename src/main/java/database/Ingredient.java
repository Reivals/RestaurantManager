package database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Michal on 22.03.2019
 */
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Ingredient {

    private enum Type{
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
    private String calories;

    @Column(name = "ING_TYPE")
    private Type type;


    public Ingredient(String name, String calories, Type type) {
        this.name = name;
        this.calories = calories;
        this.type = type;
    }
}
