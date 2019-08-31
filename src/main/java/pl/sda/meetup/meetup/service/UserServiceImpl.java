package pl.sda.meetup.meetup.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.meetup.meetup.dto.UserDto;
import pl.sda.meetup.meetup.exception.UserExistsException;
import pl.sda.meetup.meetup.mapper.manual.ManualUserMapper;
import pl.sda.meetup.meetup.model.User;
import pl.sda.meetup.meetup.repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ManualUserMapper manualUserMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ManualUserMapper manualUserMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.manualUserMapper = manualUserMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void save(UserDto userDto) {
        if (findUserByEmail(userDto.getEmail()).isPresent()){
            throw new UserExistsException("User with email " + userDto.getEmail() + " already exists!");
        }
        userRepository.save(manualUserMapper.userDtoToUser(userDto));
        log.info("saved user " + userDto.getUsername());
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
