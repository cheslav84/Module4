package module.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "produced_amount")
    private int producedAmount;

    @Column(name = "spent_amount")
    private int spentAmount;

    @Column(name = "measure_units")
    private String measureUnits;

    public Fuel(String measureUnits) {
        this.measureUnits = measureUnits;
    }

    public int getRemainder() {
        return producedAmount - spentAmount;
    }
}
