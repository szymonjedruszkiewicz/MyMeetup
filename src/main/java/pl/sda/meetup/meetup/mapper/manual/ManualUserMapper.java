package pl.sda.meetup.meetup.mapper.manual;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.meetup.meetup.dto.UserDto;
import pl.sda.meetup.meetup.model.User;

@Component
public class ManualUserMapper {

    private final PasswordEncoder passwordEncoder;

    public ManualUserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public User userDtoToUser(UserDto userLoginDto) {
        return User.builder()
                .email(userLoginDto.getEmail())
                .username(userLoginDto.getUsername())
                .passwordHash(passwordEncoder.encode(userLoginDto.getPassword()))
                .build();
    }

    public UserDto userToUserDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }


}
