package module.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StatisticsDTO {
    private long itemsAmount;
    private long brokenMicrocircuits;
    private long producedFuel;
    private long spentFuel;
}
