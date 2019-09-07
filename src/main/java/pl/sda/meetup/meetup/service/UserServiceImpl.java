package pl.sda.meetup.meetup.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.meetup.meetup.dto.UserDto;
import pl.sda.meetup.meetup.exception.NoEventException;
import pl.sda.meetup.meetup.exception.NoUserException;
import pl.sda.meetup.meetup.exception.UserExistsException;
import pl.sda.meetup.meetup.mapper.manual.ManualUserMapper;
import pl.sda.meetup.meetup.model.Event;
import pl.sda.meetup.meetup.model.User;
import pl.sda.meetup.meetup.repository.EventRepository;
import pl.sda.meetup.meetup.repository.UserRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ManualUserMapper manualUserMapper;
    private final EventRepository eventRepository;
    private final UserContextService userContextService;

    public UserServiceImpl(UserRepository userRepository, ManualUserMapper manualUserMapper, EventRepository eventRepository, UserContextService userContextService) {
        this.userRepository = userRepository;
        this.manualUserMapper = manualUserMapper;
        this.eventRepository = eventRepository;
        this.userContextService = userContextService;
    }


    @Override
    public void save(UserDto userDto) {
        if (findUserByEmail(userDto.getEmail()).isPresent()) {
            throw new UserExistsException("User with email " + userDto.getEmail() + " already exists!");
        }
        userRepository.save(manualUserMapper.userDtoToUser(userDto));
        log.info("saved user " + userDto.getUsername());
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Set<UserDto> listUsersRegisteredToEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NoEventException("no user found in db"));
        return event.getRegisteredUsers().stream()
                .map(manualUserMapper::userToUserDto)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean checkIfIsRegistered(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NoEventException("no user found in db"));
        User user = userRepository.findUserByEmail(userContextService.getLoggedUserName()).orElseThrow(() -> new NoUserException("user not found in db"));
        return event.getRegisteredUsers().contains(user);
    }

    @Override
    public boolean manageEventRegistration(String sign, Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NoEventException("No event of id: " + eventId + " found in db"));
        User user = userRepository.findUserByEmail(userContextService.getLoggedUserName()).orElseThrow(() -> new NoUserException("user not found in db"));

        switch (sign) {
            case "sign":
                event.addUserToEvent(user);
                eventRepository.save(event);
                return true;
            case "unsign":
                event.removeUserFromEvent(user);
                eventRepository.save(event);
                return false;
            default:
                throw new RuntimeException("could not manage event registration");
        }
    }
}
