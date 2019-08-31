package pl.sda.meetup.meetup.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Builder
@Data
@Getter
@Setter
public class EventDto {

    @NotBlank(message = "not blank")
    @NotNull(message = "not null")
    private String title;

    @Size(min = 20, message = "min 20 letters")
    private String description;

    private UserDto userDto;

    @Future(message = "wrong date")
    private LocalDateTime start;

    @Future(message = "wrong date")
    private LocalDateTime end;
}
