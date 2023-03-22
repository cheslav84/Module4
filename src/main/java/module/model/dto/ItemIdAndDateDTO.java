package module.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemIdAndDateDTO {
    private String id;
    private Date date;

}
