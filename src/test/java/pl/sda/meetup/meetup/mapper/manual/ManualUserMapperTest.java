package pl.sda.meetup.meetup.mapper.manual;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.meetup.meetup.exception.NoUserException;

class ManualUserMapperTest {

    @Autowired
    ManualUserMapper mapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("should return null when testing mapping from userDto to user")
    void userDtoToUser() {
        Assertions.assertThrows(NoUserException.class, () -> mapper.userDtoToUser(null));

    }

    @Test
    void userToUserDto() {
    }
}