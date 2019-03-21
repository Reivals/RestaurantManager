package database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Michal on 21.03.2019
 */

@Entity
@Table(name = "TestTable")
@Getter
@Setter
@NoArgsConstructor
public class DummyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code_a")
    private String code;

    @Column(name = "value_a")
    private String value;

    public DummyData(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
