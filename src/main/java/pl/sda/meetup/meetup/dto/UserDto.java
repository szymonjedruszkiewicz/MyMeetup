package pl.sda.meetup.meetup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.meetup.meetup.model.Role;

import javax.validation.constraints.*;
import java.util.Set;

@Component
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
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

    private Set<Role> roles;
}

