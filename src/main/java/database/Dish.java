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
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIS_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIS_SINGLE_ORDER")
    private SingleOrder singleOrder;
}
