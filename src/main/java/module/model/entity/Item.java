package module.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fuel_id")
    private Fuel fuel;

    @Column(name = "production_duration")
    private int productionDuration;

    @Column(name = "production_date")
    private Date productionDate;

    @Column(name = "broken_microcircuits")
    private int brokenMicrocircuits;

    @Transient
    private int firstStagePercentages;

    @Transient
    private int secondStagePercentages;

    @Transient
    private int thirdStagePercentages;
}
