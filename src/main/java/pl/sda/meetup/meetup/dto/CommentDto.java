package pl.sda.meetup.meetup.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {

    @Min(10)
    @Max(500)
    private String description;

    private UserDto userDto;

    private EventDto eventDto;

    private LocalDateTime dateOfCreation;
}
