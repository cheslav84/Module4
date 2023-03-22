package module.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO {
    private String id;
    private int productionDuration;
    private Date productionDate;
    private int brokenMicrocircuits;
    private int producedFuel;
    private int spentFuel;
}
