package pl.sda.meetup.meetup.service;

import pl.sda.meetup.meetup.dto.UserDto;
import pl.sda.meetup.meetup.model.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    void save(UserDto userDto);

    Optional<User> findUserByEmail(String email);

    Set<UserDto> listUsersRegisteredToEvent(Long eventId);

    boolean checkIfIsRegistered(Long eventId);

    boolean manageEventRegistration(String sign, Long eventId);



}
