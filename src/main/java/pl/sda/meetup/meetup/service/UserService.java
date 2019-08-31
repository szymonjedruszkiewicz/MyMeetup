package pl.sda.meetup.meetup.service;

import pl.sda.meetup.meetup.dto.UserDto;
import pl.sda.meetup.meetup.model.User;

import java.util.Optional;

public interface UserService {

    void save(UserDto userDto);

    Optional<User> findUserByEmail(String email);

}
