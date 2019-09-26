package pl.sda.meetup.meetup.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private Long id;

    @NotBlank(message = "not blank")
    @NotNull(message = "not null")
    private String title;

    @Size(min = 20, message = "min 20 letters")
    private String description;

    private UserDto userDto;

    @Future
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate start;

    @Future
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate end;
}
