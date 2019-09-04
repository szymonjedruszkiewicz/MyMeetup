package pl.sda.meetup.meetup.dto;

import lombok.*;
import pl.sda.meetup.meetup.model.Event;
import pl.sda.meetup.meetup.model.User;

import java.time.LocalDateTime;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private String description;

    private UserDto userDto;

    private EventDto eventDto;

    private LocalDateTime dateOfCreation;
}
