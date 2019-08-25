package pl.sda.meetup.meetup.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.meetup.meetup.model.UserRole;

import javax.validation.constraints.*;

@Component
@Data
@NoArgsConstructor
@Builder
public class UserDto {

    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 30)
    private String password;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    private UserRole userRole;
}

