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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLI_ID")
    private Long id;

    @Column(name = "CLI_FIRST_NAME")
    private String firstName;

    @Column(name = "CLI_LAST_NAME")
    private String secondName;

    @Column(name = "CLI_TABLE_NUMBER")
    private Long tableNumber;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private SingleOrder singleOrder;





}
