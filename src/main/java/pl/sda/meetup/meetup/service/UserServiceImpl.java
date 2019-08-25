package pl.sda.meetup.meetup.service;

import org.springframework.stereotype.Service;
import pl.sda.meetup.meetup.dto.UserDto;
import pl.sda.meetup.meetup.mapper.manual.ManualUserMapper;
import pl.sda.meetup.meetup.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void save(UserDto userDto) {

    }
}
