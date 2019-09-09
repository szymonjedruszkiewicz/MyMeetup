package pl.sda.meetup.meetup.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {

    @Length(max = 500)
    private String description;

    private UserDto userDto;

    private EventDto eventDto;

    private LocalDateTime dateOfCreation;
}
