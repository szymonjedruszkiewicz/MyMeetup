package pl.sda.meetup.meetup.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Component
@Data
@NoArgsConstructor
public class UserDTO {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 8, max = 30)
    private String password;

    @Size(min = 1, max = 50)
    private String username;

}
