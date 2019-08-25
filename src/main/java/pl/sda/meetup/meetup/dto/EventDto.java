package pl.sda.meetup.meetup.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.sda.meetup.meetup.model.User;

import java.time.LocalDateTime;

@Builder
@Data
@Getter
@Setter
public class EventDto {

    private String title;

    private String description;

    private User user;

    private LocalDateTime start;

    private LocalDateTime end;
}
