package pl.sda.meetup.meetup.mapper.manual;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.meetup.meetup.dto.UserDto;
import pl.sda.meetup.meetup.exception.NoUserException;
import pl.sda.meetup.meetup.model.Role;
import pl.sda.meetup.meetup.model.User;
import pl.sda.meetup.meetup.repository.RoleRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class ManualUserMapper {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public ManualUserMapper(PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    public User userDtoToUser(UserDto userLoginDto) {
        if (userLoginDto == null) {
            throw new NoUserException("userDto to map is null");
        } else {

            Optional<Role> user = roleRepository.findByRoleName("user");
            Set<Role> roleSet = new HashSet<>();
            user.ifPresent(roleSet::add);

            return User.builder()
                    .email(userLoginDto.getEmail())
                    .username(userLoginDto.getUsername())
                    .passwordHash(passwordEncoder.encode(userLoginDto.getPassword()))
                    .roles(roleSet)
                    .build();
        }
    }

    public UserDto userToUserDto(User user) {
        if (user == null) {
            throw new NoUserException("user to map is null");
        }
        return UserDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }


}
